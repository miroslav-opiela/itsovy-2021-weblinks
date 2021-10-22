package sk.itsovy.android.weblinks

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), OnWeblinkClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // pri vyrobeni adaptera povie aktivita ze je listenerom
        adapter = WeblinksAdapter(this)
        recyclerView.adapter = adapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteWeblinkCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_add -> {
                Toast.makeText(this, "ADD", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    lateinit var adapter: WeblinksAdapter

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                // it.data je intent
                val weblink = it.data?.getSerializableExtra(DetailActivity.WEBLINK_TAG) as Weblink
                Toast.makeText(this, "novy title" + weblink.title, Toast.LENGTH_SHORT).show()
                adapter.update(weblink)
            }
        }

    override fun onWeblinkClick(weblink: Weblink) {
        // tu sa spusti nova aktivita

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.WEBLINK_TAG, weblink)
        }
        //alternativne - bez scope funkcie - intent.putExtra(DetailActivity.WEBLINK_TAG, weblink)

        // spustenie aktivity bez cakania na vysledok
        //startActivity(intent)

        // spustenie aktivity a callback pri vysledku
        resultLauncher.launch(intent)
    }

    override fun onWeblinkLongClick(weblink: Weblink) {
        //Toast.makeText(this, weblink.toString(), Toast.LENGTH_LONG).show()

        // alebo to vlozit priamo v konstruktore
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse(weblink.url)
        startActivity(intent)
    }
}