package sk.itsovy.android.weblinks

import java.io.Serializable
import java.util.*

data class Weblink(var title: String, var rating: Int) : Serializable {
    val uuid: UUID = UUID.randomUUID()
    lateinit var url : String

    init {
        updateUrl()
    }

    fun updateUrl() {
        url = "https://en.wikipedia.org/wiki/" + title.replace(' ', '_')
    }
}

// updateUrl by sa mohlo volat automaticky pri nastaveni title
