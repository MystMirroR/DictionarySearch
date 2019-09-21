package ru.skyeng.dictionary.data.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import ru.skyeng.dictionary.data.model.meanings.Meanings
import ru.skyeng.dictionary.data.model.search.Word

interface SkyEngAPI {

    /**
     * Search
     */
    @GET("words/search")
    fun searchWordsAsync(@Query("search") search: String, @Query("page") page: Int?, @Query("pageSize") pageSize: Int?)
            : Deferred<List<Word>>

    /**
     * Meaning
     * @param quality 0..100
     */
    @GET("meanings")
    fun getMeaningsAsync(@Query("width") width: String?, @Query("quality") quality: Int?, @Query("ids") ids: String)
            : Deferred<List<Meanings>>

}