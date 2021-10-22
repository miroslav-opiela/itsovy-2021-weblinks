package sk.itsovy.android.weblinks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var weblink: Weblink

    companion object {
        const val WEBLINK_TAG = "weblink"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(findViewById(R.id.toolbarDetailActivity))

        weblink = intent.getSerializableExtra(WEBLINK_TAG) as Weblink
        val textView: TextView = findViewById(R.id.weblinkDetailTextView)
        editText = findViewById(R.id.weblinkDetailEditText)

        textView.text = weblink.toString()
        editText.setText(weblink.title)

    }


    fun save(view: View) {
        val intent = Intent()

        weblink.title = editText.text.toString()

        intent.putExtra(WEBLINK_TAG, weblink)

       /* if (weblink.title.isEmpty()) {
            setResult(RESULT_CANCELED)
        } else {
            setResult(RESULT_OK, intent)
        }*/

        setResult(if (weblink.title.isEmpty()) RESULT_CANCELED else RESULT_OK, intent)

        finish()
    }
}