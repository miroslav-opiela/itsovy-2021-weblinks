package sk.itsovy.android.weblinks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val WEBLINK_TAG = "weblink"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val weblink = intent.getSerializableExtra(WEBLINK_TAG)
        val textView : TextView = findViewById(R.id.weblinkDetailTextView)
        textView.text = weblink.toString()
    }

    fun save(view: View) {}
}