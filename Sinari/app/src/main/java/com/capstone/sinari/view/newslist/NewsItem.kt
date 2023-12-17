package com.capstone.sinari.view.newslist

data class NewsItem(
    val imageResource: Int,
    val location: String,
    val title: String,
    val source: String,
    val netral: String,
    val bias: String,
    val subjektif: String
)