package sk.itsovy.android.weblinks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Weblink::class], version = 1, exportSchema = false)
@TypeConverters(UuidConverters::class)
public abstract class WeblinkRoomDatabase : RoomDatabase() {

    // funkcia ktora vrati weblinkDao - toto neimplementujeme my
    abstract fun weblinkDao(): WeblinkDao

    companion object {
        @Volatile
        private var INSTANCE: WeblinkRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WeblinkRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // vyrobim novu instanciu databazy
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeblinkRoomDatabase::class.java,
                    "weblink_database"
                ).addCallback(WeblinkDatabaseCallback(scope))
                    .build()
                // vlozim do inst. premennej
                INSTANCE = instance
                // vratim vysledok
                instance
            }
        }
    }

    private class WeblinkDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.weblinkDao())
                }
            }
        }

        suspend fun populateDatabase(dao: WeblinkDao) {
            dao.deleteAll()

            dao.insert(Weblink("Kosice", 3))
            dao.insert(Weblink("Wikipedia", 2))
            dao.insert(Weblink("Slovakia", 0))
        }

    }

}