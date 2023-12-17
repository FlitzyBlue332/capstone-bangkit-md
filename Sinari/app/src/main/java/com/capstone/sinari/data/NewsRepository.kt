package com.capstone.sinari.data

import com.capstone.sinari.data.response.NewsItem
import com.capstone.sinari.data.response.TopicItem
import com.capstone.sinari.data.retrofit.ApiConfig
import com.capstone.sinari.data.retrofit.ApiService

class NewsRepository {
    private var apiService = ApiConfig.getApiService()

    suspend fun getApiService(): ApiService {
        return apiService
    }

    suspend fun getNews(referer: String): List<NewsItem> {
        return apiService.getNews(referer).news!!
    }

    suspend fun getHeadlines(): List<TopicItem> {
        return apiService.getTopic().headlines!!
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository()
            }.also { instance = it }
    }
}