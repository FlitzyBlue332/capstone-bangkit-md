package com.capstone.sinari.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.capstone.sinari.data.response.NewsItem
import com.capstone.sinari.data.response.TopicItem
import com.capstone.sinari.data.retrofit.ApiConfig
import com.capstone.sinari.data.retrofit.ApiService

class NewsRepository {
    private var apiService = ApiConfig.getApiService()
    private var newsPagingSource = NewsPagingSource(apiService)

    suspend fun getApiService(): ApiService {
        return apiService
    }

    suspend fun getNews(referer: String): List<NewsItem> {
        return apiService.getNews(referer).news!!
    }

    fun getHeadlinesPager(): LiveData<PagingData<TopicItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                newsPagingSource
            }
        ).liveData
    }

    fun getNewsPagerExceptionMessage(): LiveData<String?>{
        return newsPagingSource.getExceptionMessage()
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