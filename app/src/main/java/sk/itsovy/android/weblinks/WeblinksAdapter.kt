package sk.itsovy.android.weblinks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// val vyrobi z listener aj premennu, bez val by to bol len parameter konsturktora
class WeblinksAdapter(val listener: OnWeblinkClickListener) : RecyclerView.Adapter<WeblinksAdapter.WeblinksViewHolder>() {

    // data pre recycler view, aktualizuju sa na zaklade live data
    // na zaciatku tam je prazdny zoznam
   var cachedWeblinks : List<Weblink> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class WeblinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // hladam widget v ramci layoutu jednej polozky, ktory pride ako parameter
        val textView: TextView = itemView.findViewById(R.id.textViewWeblinkTitle)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBarWeblink)

        fun bind(weblink: Weblink, listener: OnWeblinkClickListener) {
            textView.text = weblink.title
            ratingBar.rating = weblink.rating.toFloat()

            textView.setOnClickListener {
                listener.onWeblinkClick(weblink)
            }
            textView.setOnLongClickListener {
                listener.onWeblinkLongClick(weblink)
                // treba vratit true, lebo metoda onLongClick vrati boolean
                true
            }
            // aktualizuje objekt ked sa zmeni rating po kliknut na ratingbar
            ratingBar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener {
                _, rating, _ -> weblink.rating = rating.toInt()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeblinksViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.weblink_item_layout, parent, false)
        return WeblinksViewHolder(layout)
    }

    override fun onBindViewHolder(holder: WeblinksViewHolder, position: Int) {
        holder.bind(cachedWeblinks[position], listener)
    }

    // vrati pocet poloziek v zozname - mnozstvo dat
    override fun getItemCount(): Int = cachedWeblinks.size

}