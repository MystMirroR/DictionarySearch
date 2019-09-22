package ru.skyeng.dictionary.ui.meaning

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.skyeng.dictionary.data.APIResult
import ru.skyeng.dictionary.data.model.meanings.Meanings
import ru.skyeng.dictionary.data.remote.DictionaryRepo
import ru.skyeng.dictionary.utils.SingleLiveEvent
import kotlin.coroutines.CoroutineContext

class MeaningViewModel(private val apiRepo: DictionaryRepo) : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    val showLoading = MutableLiveData<Boolean>()
    val meaningList = MutableLiveData<List<Meanings>>()
    val showError = SingleLiveEvent<String>()

    fun getMeaning(ids: String) {
        //TODO Loading progress

        launch {
            when (val result = withContext(Dispatchers.IO) { apiRepo.getMeaning(ids) }) {
                is APIResult.Success -> meaningList.value = result.data
                is APIResult.Error -> showError.value = result.exception.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}