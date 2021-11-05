package sk.itsovy.android.weblinks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import sk.itsovy.android.weblinks.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var weblink: Weblink

    // nazov triedy je podla xml layoutu + slovo binding
    lateinit var binding: ActivityDetailBinding

    companion object {
        const val WEBLINK_TAG = "weblink"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewBinding
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetailActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        weblink = intent.getSerializableExtra(WEBLINK_TAG) as Weblink

        binding.weblinkDetailTextView.text = weblink.toString()
        binding.weblinkDetailEditText.setText(weblink.title)

        binding.buttonSave.setOnClickListener { save() }
    }


    fun save() {
        val intent = Intent()

        weblink.title = binding.weblinkDetailEditText.text.toString()

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