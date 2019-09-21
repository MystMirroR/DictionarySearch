package ru.skyeng.dictionary.data.model.meanings


import com.google.gson.annotations.SerializedName

data class Meanings(
    @SerializedName("alternativeTranslations")
    val alternativeTranslations: List<AlternativeTranslation?>?,
    @SerializedName("definition")
    val definition: Definition?,
    @SerializedName("difficultyLevel")
    val difficultyLevel: Int?, // 1..6
    @SerializedName("examples")
    val examples: List<Example?>?,
    @SerializedName("id")
    val id: String?, // 254682
    @SerializedName("images")
    val images: List<Image?>?,
    @SerializedName("meaningsWithSimilarTranslation")
    val meaningsWithSimilarTranslation: List<MeaningsWithSimilarTranslation?>?,
    @SerializedName("mnemonics")
    val mnemonics: String?,
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String?, // n
    @SerializedName("prefix")
    val prefix: String?,
    @SerializedName("properties")
    val properties: Properties?,
    @SerializedName("soundUrl")
    val soundUrl: String?, // //dmsbj0x9fxpml.cloudfront.net/4822e06127b8e257338d9fc6b867bb363ba80894b02ba8766577e8e2be2e1b1e.mp3
    @SerializedName("text")
    val text: String?, // teledensity
    @SerializedName("transcription")
    val transcription: String?, // ˈtɛlɪˌdɛnsɪti
    @SerializedName("translation")
    val translation: Translation?,
    @SerializedName("updatedAt")
    val updatedAt: String?, // 2019-03-28 05:15:30
    @SerializedName("wordId")
    val wordId: Int? // 179064
)