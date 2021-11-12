package sk.itsovy.android.weblinks

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

// aplikacia je globalna vec dostupna zo vsetkych aktivit
class WeblinkApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    // by lazy je delegovane - lambda funkcia vo vnutri sa vola iba prvykrat, potom sa uz vrati vypocitany vysledok
    // teda mame iba jeden vyskyt database a repository
    // databaza sa vyrobi pomocou statickej funkcie
    val database by lazy { WeblinkRoomDatabase.getDatabase(this, applicationScope) }
    // repository sa vyrobi novy objekt - v kotline slovo new nepiseme
    val repository by lazy { WeblinkRepository(database.weblinkDao()) }

}