package sk.itsovy.android.weblinks

import java.io.Serializable
import java.util.*

// nepouzivame data class lebo potrebujeme upravovat konstruktor, settre a pod.

// rating ma default hodnotu - preto je tento parameter v konstruktore optional
class Weblink(_title: String, var rating: Int = 0) : Serializable {

    var title : String = _title
        set(value) {
            // backing field
            field = value
            updateUrl()
        }

    private fun updateUrl() {
        url = "https://en.wikipedia.org/wiki/" + title.replace(' ', '_')
    }

    lateinit var url : String
        // zakazeme nastavovat hodnotu url mimo tejto triedy
        private set
    init {
        updateUrl()
    }

    // mozno v buducnosti budem potrebovat nastavit uuid - potom to doplnime
    val uuid: UUID = UUID.randomUUID()

    override fun toString(): String {
        return "Weblink(rating=$rating, title='$title')"
    }

    // volitelne mozme vygenerovat hashCode a equals

    companion object {
        fun emptyWeblink() : Weblink = Weblink(_title = "")
    }
}