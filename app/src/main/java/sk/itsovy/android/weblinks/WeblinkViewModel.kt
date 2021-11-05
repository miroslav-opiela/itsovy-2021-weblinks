package sk.itsovy.android.weblinks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// view model prezije dlhsie ako aktivita, preto uchovava jej data
class WeblinkViewModel(private val repository: WeblinkRepository) : ViewModel() {

    // live data ktore sleduje aktivita a podla toho meni UI
    val allWeblinks = repository.allWeblinks.asLiveData()

    //Launching a new coroutine to insert the data in a non-blocking way
    fun insert(weblink: Weblink) = viewModelScope.launch {
        repository.insert(weblink)
    }

    fun update(weblink: Weblink) = viewModelScope.launch {
        repository.update(weblink)
    }

    fun delete(weblink: Weblink) = viewModelScope.launch {
        repository.delete(weblink)
    }

    // toto je skopirovane - tovaren na vyrabanie view modelu
    class WeblinkViewModelFactory(private val repository: WeblinkRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeblinkViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeblinkViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}