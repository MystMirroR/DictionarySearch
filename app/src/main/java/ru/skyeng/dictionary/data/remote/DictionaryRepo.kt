package ru.skyeng.dictionary.data.remote

import ru.skyeng.dictionary.data.APIResult
import ru.skyeng.dictionary.data.api.SkyEngAPI
import ru.skyeng.dictionary.data.model.meanings.Meanings
import ru.skyeng.dictionary.data.model.search.Word

interface DictionaryRepo {
    suspend fun searchWord(search: String): APIResult<List<Word>>
    suspend fun getMeaning(ids: String): APIResult<List<Meanings>>

}

class DictionaryRepoImpl(private val skyengAPI: SkyEngAPI) : DictionaryRepo {
    override suspend fun getMeaning(ids: String): APIResult<List<Meanings>> {
        return try {
            val result = skyengAPI.getMeaningsAsync(null, 90, ids).await()
            APIResult.Success(result)
        } catch (ex: Exception) {
            APIResult.Error(ex)
        }
    }

    override suspend fun searchWord(search: String): APIResult<List<Word>> {
        return try {
            val result = skyengAPI.searchWordsAsync(search, null, null).await()
            APIResult.Success(result)
        } catch (ex: Exception) {
            APIResult.Error(ex)
        }
    }
}