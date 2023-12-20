package com.capstone.sinari.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class NewsResponse(

	@field:SerializedName("news")
	val news: List<NewsItem>? = null,

	@field:SerializedName("lastUpdated")
	val lastUpdated: String? = null,

	@field:SerializedName("aggregateMetrics")
	val aggregateMetrics: AggregateMetrics
) : Parcelable

@Parcelize
data class NewsItem(

	@field:SerializedName("thumbnail")
	val thumbnail: String,

	@field:SerializedName("publishedAt")
	val publishedAt: String,

	@field:SerializedName("publisherName")
	val publisherName: String,

	@field:SerializedName("publisherLogo")
	val publisherLogo: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("excerpt")
	val excerpt: String,

	@field:SerializedName("url")
	val url: String,

	@field:SerializedName("subjectivity")
	val subjectivity: Float,

	@field:SerializedName("bias")
	val bias: Float,

	@field:SerializedName("right_tendency")
	val rightTendency: Float,

	@field:SerializedName("center_tendency")
	val centerTendency: Float,

	@field:SerializedName("left_tendency")
	val leftTendency: Float,

) : Parcelable
