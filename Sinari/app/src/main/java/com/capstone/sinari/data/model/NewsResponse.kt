package com.capstone.sinari.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse (
    @SerializedName("items")
    val items : ArrayList<NewsItem>
)