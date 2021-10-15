package sk.itsovy.android.weblinks

interface OnWeblinkClickListener {

    fun onWeblinkClick(weblink: Weblink)

    fun onWeblinkLongClick(weblink: Weblink)
}