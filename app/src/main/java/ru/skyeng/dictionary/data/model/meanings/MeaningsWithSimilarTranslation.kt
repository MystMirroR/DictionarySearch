package ru.skyeng.dictionary.data.model.meanings


import com.google.gson.annotations.SerializedName

data class MeaningsWithSimilarTranslation(
    @SerializedName("frequencyPercent")
    val frequencyPercent: String?, // 100.0
    @SerializedName("meaningId")
    val meaningId: Int?, // 254682
    @SerializedName("partOfSpeechAbbreviation")
    val partOfSpeechAbbreviation: String?, // сущ.
    @SerializedName("translation")
    val translation: Translation?
)