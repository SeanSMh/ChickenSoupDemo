package com.sean.chickensoupforthesoul.api

import com.google.gson.annotations.SerializedName

data class ChickenSoupEntity(
    @SerializedName("code") val code: Int = 0,
    @SerializedName("msg") val msg: String? = null,
    @SerializedName("newslist") val newsList: MutableList<ChickenSoupbody> = mutableListOf<ChickenSoupbody>()
)

data class ChickenSoupbody(
    @SerializedName("content") val content: String? = null
)




