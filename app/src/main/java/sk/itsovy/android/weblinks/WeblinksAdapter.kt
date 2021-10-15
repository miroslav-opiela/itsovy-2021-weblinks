package sk.itsovy.android.weblinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeblinksAdapter : RecyclerView.Adapter<WeblinksAdapter.WeblinksViewHolder>() {

    val weblinksTitles = listOf(
        "Kaikhosru Shapurji Sorabji",
        "Primeira Idade",
        "Lynda Adams",
        "Ray Leatherwood",
        "Bawlte Rohmingthanga",
        "Psycho-Oncology",
        "Horaiclavidae",
        "Sankt Lorenz",
        "Hye-young",
        "Michael Zabel",
        "Orosh",
        "Design–bid–build",
        "Photoelectrochemical cell",
        "Andricus foecundatrix",
        "Erika Steinbach",
        "Tide Lines",
        "Cynomastix"
    )

    // list weblinkov
    val weblinks = weblinksTitles.map { Weblink(it, 3) }

    class WeblinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // hladam widget v ramci layoutu jednej polozky, ktory pride ako parameter
        val textView: TextView = itemView.findViewById(R.id.textViewWeblinkTitle)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBarWeblink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeblinksViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.weblink_item_layout, parent, false)
        return WeblinksViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WeblinksViewHolder, position: Int) {
        holder.textView.text = weblinks[position].title
        // rating bar ocakava float, lebo moze byt aj pol hviezdicky a pod.
        holder.ratingBar.rating = weblinks[position].rating.toFloat()
    }

    // vrati pocet poloziek v zozname - mnozstvo dat
    override fun getItemCount(): Int = weblinksTitles.size

}