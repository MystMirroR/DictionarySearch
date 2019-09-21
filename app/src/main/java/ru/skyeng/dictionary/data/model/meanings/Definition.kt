package ru.skyeng.dictionary.data.model.meanings


import com.google.gson.annotations.SerializedName

data class Definition(
    @SerializedName("soundUrl")
    val soundUrl: String?, // //dmsbj0x9fxpml.cloudfront.net/9159413131c24082ad4e8ab6d6b63aaa45841fbc712d258fb338b3ee69762d74.mp3
    @SerializedName("text")
    val text: String? // The number of telephone connections for every 100 people living within an area.
)