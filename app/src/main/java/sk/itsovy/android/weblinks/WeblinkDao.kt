package sk.itsovy.android.weblinks

import androidx.room.*

@Dao
interface WeblinkDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weblink: Weblink)

    @Query("SELECT * FROM weblinks")
    fun getAllWeblinks() : List<Weblink>

    @Query("DELETE FROM weblinks")
    fun deleteAll()

    @Delete
    fun deleteWeblink(weblink: Weblink)

    // dvojbodka nam oznaci ze sa tam dava premenna ktora je ako parameter
    @Query("DELETE FROM weblinks WHERE title=:title")
    fun deleteWeblink(title: String)

    @Update
    fun update(weblink: Weblink)

}