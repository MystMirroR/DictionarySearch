package ru.skyeng.dictionary.data.model.search


import com.google.gson.annotations.SerializedName

data class Word(
    @SerializedName("id")
    val id: Int?, // 319
    @SerializedName("meanings")
    val meanings: List<Meaning?>?,
    @SerializedName("text")
    val text: String? // open
)