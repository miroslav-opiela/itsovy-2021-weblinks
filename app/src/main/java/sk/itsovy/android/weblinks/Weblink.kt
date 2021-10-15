package sk.itsovy.android.weblinks

data class Weblink(val title: String, var rating: Int) {
    val url = "https://en.wikipedia.org/wiki/" + title.replace(' ', '_')
}