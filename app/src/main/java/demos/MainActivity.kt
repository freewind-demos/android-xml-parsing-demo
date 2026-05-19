package demos.android.xml.parsing.demo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val textView = findViewById<TextView>(R.id.textView)
        
        val xml = "<user><name>张三</name><age>25</age></user>"
        val parser = XmlPullParserFactory.newInstance().newPullParser()
        parser.setInput(xml.byteInputStream(), "UTF-8")
        
        var result = ""
        var eventType = parser.eventType
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.TEXT) {
                result += parser.text
            }
            eventType = parser.next()
        }
        
        textView.text = result
    }
}
