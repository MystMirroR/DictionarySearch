package ru.skyeng.dictionary.data.model.meanings


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url")
    val url: String? // //static.skyeng.ru/image/download/project/dictionary/id/152102/width/200/height/150/quality/50
)