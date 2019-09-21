package ru.skyeng.dictionary.data.model.meanings


import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("collocation")
    val collocation: Boolean?, // false
    @SerializedName("countability")
    val countability: String?, // u
    @SerializedName("falseFriends")
    val falseFriends: List<Any?>?,
    @SerializedName("plurality")
    val plurality: String? // s
)