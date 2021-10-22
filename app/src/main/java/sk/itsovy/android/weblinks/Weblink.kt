package sk.itsovy.android.weblinks

import java.io.Serializable
import java.util.*

data class Weblink(var title: String, var rating: Int) : Serializable {
    val uuid: UUID = UUID.randomUUID()
    val url = "https://en.wikipedia.org/wiki/" + title.replace(' ', '_')
}
