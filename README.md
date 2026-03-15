# Android XML 解析演示

## 简介

本 Demo 演示 Android 中 XML 的解析方法，展示如何使用 XmlPullParser 解析 XML 数据。

## 基本原理

XML（eXtensible Markup Language）是一种常用的数据格式，在 Android 中常用于：
- 配置文件
- 网络数据传输
- 数据存储

Android 支持三种 XML 解析方式：

1. **DOM 解析器**
   - 将整个文档加载到内存
   - 优点：可以随机访问节点
   - 缺点：占用内存大
   - 适用：小文件

2. **SAX 解析器**
   - 流式解析，事件驱动
   - 优点：占用内存小，性能好
   - 缺点：只能顺序读取
   - 适用：大文件

3. **XmlPullParser**
   - Android 原生，API 类似 SAX
   - 优点：简单易用，性能好
   - 推荐使用

## 启动和使用

### 环境要求
- Android Studio
- JDK 17
- Gradle 8.x

### 安装和运行

1. 用 Android Studio 打开项目
2. 连接 Android 设备或模拟器
3. 点击 Run 运行

### 使用方法
- 运行后将看到解析后的 XML 内容

## 教程

### 什么是 XML 解析？

XML 解析是将 XML 格式的数据转换为程序可处理的对象的过程。Android 提供了多种解析器，其中 XmlPullParser 是最推荐的方式。

### XmlPullParser 基本用法

1. 创建解析器：

```kotlin
val parser = XmlPullParserFactory.newInstance().newPullParser()
```

2. 设置输入源：

```kotlin
parser.setInput(inputStream, "UTF-8")
```

3. 解析事件：

```kotlin
var eventType = parser.eventType
while (eventType != XmlPullParser.END_DOCUMENT) {
    when (eventType) {
        XmlPullParser.START_TAG -> {
            // 开始标签
            val name = parser.name
        }
        XmlPullParser.TEXT -> {
            // 文本内容
            val text = parser.text
        }
        XmlPullParser.END_TAG -> {
            // 结束标签
        }
    }
    eventType = parser.next()
}
```

### 事件类型说明

| 事件 | 说明 |
|------|------|
| START_DOCUMENT | 文档开始 |
| START_TAG | 开始标签 |
| TEXT | 文本内容 |
| END_TAG | 结束标签 |
| END_DOCUMENT | 文档结束 |

### 解析示例

```xml
<users>
    <user>
        <name>张三</name>
        <age>25</age>
    </user>
</users>
```

解析代码：

```kotlin
while (parser.eventType != XmlPullParser.END_DOCUMENT) {
    if (parser.eventType == XmlPullParser.START_TAG) {
        if (parser.name == "name") {
            val name = parser.nextText()
        }
    }
    parser.next()
}
```

## 关键代码详解

### MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 获取 TextView 组件
        val textView = findViewById<TextView>(R.id.textView)

        // 2. 准备 XML 字符串
        val xml = "<user><name>张三</name><age>25</age></user>"

        // 3. 创建 XmlPullParser
        val parser = XmlPullParserFactory.newInstance().newPullParser()

        // 4. 设置输入源
        // 将字符串转换为字节流
        parser.setInput(xml.byteInputStream(), "UTF-8")

        // 5. 解析 XML
        var result = ""
        var eventType = parser.eventType

        // 循环读取直到文档结束
        while (eventType != XmlPullParser.END_DOCUMENT) {
            // 只提取文本内容节点
            if (eventType == XmlPullParser.TEXT) {
                result += parser.text
            }
            // 移动到下一个事件
            eventType = parser.next()
        }

        // 6. 显示结果
        textView.text = result
    }
}
```

### activity_main.xml

```xml
<!-- 根布局：垂直线性布局 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- 标题 -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="XML 解析演示"
        android:textSize="20sp"
        android:textStyle="bold"
        android:gravity="center" />

    <!-- 显示解析结果的 TextView -->
    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:fontFamily="monospace"
        android:paddingTop="16dp" />
</LinearLayout>
```

### 常用解析器对比

| 解析器 | 内存占用 | 速度 | 适用场景 |
|--------|----------|------|----------|
| DOM | 高 | 中 | 小文件、随机访问 |
| SAX | 低 | 快 | 大文件、顺序读取 |
| XmlPullParser | 低 | 快 | 推荐使用 |
