package sk.itsovy.android.weblinks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weblink::class], version = 1, exportSchema = false)
public abstract class WeblinkRoomDatabase : RoomDatabase() {

    // funkcia ktora vrati weblinkDao - toto neimplementujeme my
    abstract fun weblinkDao(): WeblinkDao

    companion object {
        @Volatile
        private var INSTANCE: WeblinkRoomDatabase? = null

        fun getDatabase(context: Context): WeblinkRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                // vyrobim novu instanciu databazy
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeblinkRoomDatabase::class.java,
                    "weblink_database"
                ).build()
                // vlozim do inst. premennej
                INSTANCE = instance
                // vratim vysledok
                instance
            }
        }
    }

}