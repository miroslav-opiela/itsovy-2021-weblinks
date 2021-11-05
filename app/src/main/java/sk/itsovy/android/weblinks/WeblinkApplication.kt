package sk.itsovy.android.weblinks

import android.app.Application

// aplikacia je globalna vec dostupna zo vsetkych aktivit
class WeblinkApplication : Application() {

    // by lazy je delegovane - lambda funkcia vo vnutri sa vola iba prvykrat, potom sa uz vrati vypocitany vysledok
    // teda mame iba jeden vyskyt database a repository
    // databaza sa vyrobi pomocou statickej funkcie
    val database by lazy { WeblinkRoomDatabase.getDatabase(this) }
    // repository sa vyrobi novy objekt - v kotline slovo new nepiseme
    val repository by lazy { WeblinkRepository(database.weblinkDao()) }

}