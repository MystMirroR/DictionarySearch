package ru.skyeng.dictionary.data.model.search


import com.google.gson.annotations.SerializedName

data class Meaning(
    @SerializedName("id")
    val id: Int?, // 254459
    @SerializedName("imageUrl")
    val imageUrl: String?, // //static.skyeng.ru/image/download/project/dictionary/id/152755/width/640/height/480
    @SerializedName("partOfSpeechCode")
    val partOfSpeechCode: String?, // n
    @SerializedName("previewUrl")
    val previewUrl: String?, // //static.skyeng.ru/image/download/project/dictionary/id/152755/width/96/height/72
    @SerializedName("soundUrl")
    val soundUrl: String?, // //dmsbj0x9fxpml.cloudfront.net/e4112ab29ae43c6dcc1c065fea60a837be55ab4a0b416c3619121f217d6b1622.mp3
    @SerializedName("transcription")
    val transcription: String?, // ˈəʊpən
    @SerializedName("translation")
    val translation: Translation?
)