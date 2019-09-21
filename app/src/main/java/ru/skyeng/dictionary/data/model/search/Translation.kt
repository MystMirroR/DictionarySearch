package ru.skyeng.dictionary.data.model.search


import com.google.gson.annotations.SerializedName

data class Translation(
    @SerializedName("note")
    val note: String?, // провода; эл.
    @SerializedName("text")
    val text: String? // разрыв
)