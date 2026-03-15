# Android XML 解析演示

## 简介

本 Demo 演示 Android 中 XML 的解析方法。

## 教程

```kotlin
val parser = XmlPullParserFactory.newInstance().newPullParser()
parser.setInput(inputStream, "UTF-8")
while (parser.eventType != XmlPullParser.END_DOCUMENT) {
    // 解析
}
```

## 常用解析器

1. DOM: 整个文档加载到内存
2. SAX: 流式解析，事件驱动
3. XmlPullParser: Android 原生，类似 SAX
