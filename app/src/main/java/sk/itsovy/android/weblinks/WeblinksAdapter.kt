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

    val weblinksTitles = listOf(
        "Universally unique identifier",
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

    // list weblinkov, it je implicitny nazov single parametra
    val weblinks = weblinksTitles.map {
        Weblink(it, 3)
    } as MutableList<Weblink>
    //val weblinks = weblinksTitles.map { title -> Weblink(title, 3) }

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
        holder.bind(weblinks[position], listener)
    }

    // vrati pocet poloziek v zozname - mnozstvo dat
    override fun getItemCount(): Int = weblinksTitles.size

    fun update(weblink: Weblink) {
        for ((index, weblinkItem) in weblinks.withIndex()) {
            if (weblinkItem.uuid == weblink.uuid) {
                // nahradim povodny weblink v zozname novym upravenym
                weblinks[index] = weblink
                notifyItemChanged(index)
                return
            }
        }
    }

    // NEFUNGUJE - arrayIndexOutOfBoundsException
    fun remove(position: Int) {
        // aktualizujem data
        weblinks.removeAt(position)
        // dam vediet recycler view ze sa zmenili data
        notifyItemRemoved(position)
    }

}