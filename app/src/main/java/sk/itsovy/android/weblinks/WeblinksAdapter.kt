package sk.itsovy.android.weblinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeblinksAdapter : RecyclerView.Adapter<WeblinksAdapter.WeblinksViewHolder>() {

    val weblinksTitles = listOf(
        "Kaikhosru Shapurji Sorabji",
        "Primeira Idade",
        "Lynda Adams",
        "Ray Leatherwood",
        "Bawlte Rohmingthanga"
    )

    class WeblinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // hladam widget v ramci layoutu jednej polozky, ktory pride ako parameter
        val textView: TextView = itemView.findViewById(R.id.textViewWeblinkTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeblinksViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.weblink_item_layout, parent, false)
        return WeblinksViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WeblinksViewHolder, position: Int) {
        holder.textView.text = weblinksTitles[position]
    }

    // vrati pocet poloziek v zozname - mnozstvo dat
    override fun getItemCount(): Int = weblinksTitles.size

}