package ru.skyeng.dictionary.data.model.meanings


import com.google.gson.annotations.SerializedName

data class Example(
    @SerializedName("soundUrl")
    val soundUrl: String?, // //dmsbj0x9fxpml.cloudfront.net/6a4acd23e250b5b304a25cfdff19595d7132541303bf20c0dcd5fa1cdd5de072.mp3
    @SerializedName("text")
    val text: String? // [teledensity] in rural and urban areas.
)