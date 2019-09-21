package ru.skyeng.dictionary.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.skyeng.dictionary.data.APIResult
import ru.skyeng.dictionary.data.model.search.Word
import ru.skyeng.dictionary.data.remote.DictionaryRepo
import ru.skyeng.dictionary.utils.SingleLiveEvent
import kotlin.coroutines.CoroutineContext

class SearchViewModel(private val apiRepo: DictionaryRepo) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val wordsList = MutableLiveData<List<Word>>()
    val showError = SingleLiveEvent<String>()

    fun searchMeaningOfWord(search: String) {
        showLoading.value = true
        launch {
            val result = withContext(Dispatchers.IO) { apiRepo.searchWord(search) }
            showLoading.value = false
            when (result) {
                is APIResult.Success -> wordsList.value = result.data
                is APIResult.Error -> showError.value = result.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}