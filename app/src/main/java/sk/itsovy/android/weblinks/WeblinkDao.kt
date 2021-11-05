package sk.itsovy.android.weblinks

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeblinkDao {

    // suspend funkcie vedia byt pozastavene, su v ramci coroutines
    // flow vrati hodnoty z asynchronneho volania po jednej

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weblink: Weblink)

    @Query("SELECT * FROM weblinks")
    fun getAllWeblinks() : Flow<List<Weblink>>

    @Query("DELETE FROM weblinks")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteWeblink(weblink: Weblink)

    // dvojbodka nam oznaci ze sa tam dava premenna ktora je ako parameter
    @Query("DELETE FROM weblinks WHERE title=:title")
    suspend fun deleteWeblink(title: String)

    @Update
    suspend fun update(weblink: Weblink)

}