package sk.itsovy.android.weblinks

import androidx.annotation.WorkerThread

class WeblinkRepository(private val weblinkDao: WeblinkDao) {

    // toto bude typu flow
    val allWeblinks = weblinkDao.getAllWeblinks()

    // anotacia zarucuje ze to nebude v hlavnom vlakne ktore by blokovalo UI
    // suspend funkcia sa musi volav z inej suspend funkcie alebo z korutiny
    // v tomto pripade je suspend v ramci suspend funkcie
    @WorkerThread
    suspend fun insert(weblink: Weblink) {
        weblinkDao.insert(weblink)
    }

    @WorkerThread
    suspend fun update(weblink: Weblink) {
        weblinkDao.update(weblink)
    }

    @WorkerThread
    suspend fun delete(weblink: Weblink) {
        weblinkDao.deleteWeblink(weblink)
    }

}