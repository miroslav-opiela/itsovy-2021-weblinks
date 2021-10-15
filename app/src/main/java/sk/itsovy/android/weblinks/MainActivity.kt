package sk.itsovy.android.weblinks

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnWeblinkClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // pri vyrobeni adaptera povie aktivita ze je listenerom
        recyclerView.adapter = WeblinksAdapter(this)
    }

    override fun onWeblinkClick(weblink: Weblink) {
        //Toast.makeText(this, weblink.toString(), Toast.LENGTH_LONG).show()

        // alebo to vlozit priamo v konstruktore
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(weblink.url)
        startActivity(intent)
    }
}