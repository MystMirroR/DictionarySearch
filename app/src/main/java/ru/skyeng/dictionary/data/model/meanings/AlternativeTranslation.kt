package ru.skyeng.dictionary.data.model.meanings


import com.google.gson.annotations.SerializedName

data class AlternativeTranslation(
    @SerializedName("text")
    val text: String?, // unselfishness
    @SerializedName("translation")
    val translation: Translation?
)