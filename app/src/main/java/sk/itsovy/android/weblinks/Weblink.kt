package sk.itsovy.android.weblinks

import java.io.Serializable

data class Weblink(val title: String, var rating: Int) : Serializable {
    val url = "https://en.wikipedia.org/wiki/" + title.replace(' ', '_')
}