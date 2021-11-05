package sk.itsovy.android.weblinks

import androidx.room.TypeConverter
import java.util.*

class UuidConverters {

    @TypeConverter
    fun uuidToString(uuid : UUID) : String = uuid.toString()

    @TypeConverter
    fun stringToUuid(string: String) : UUID = UUID.fromString(string)

}