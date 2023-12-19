package com.capstone.sinari.data.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class TopicResponse(

	@field:SerializedName("lastUpdated")
	val lastUpdated: String? = null,

	@field:SerializedName("headlines")
	val headlines: List<TopicItem>? = null
) : Parcelable

@Parcelize
data class Topic(

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
	val url: String
) : Parcelable

@Parcelize
data class TopicItem(

	@field:SerializedName("referrer")
	val referrer: String,

	@field:SerializedName("headline")
	val topic: Topic
) : Parcelable
