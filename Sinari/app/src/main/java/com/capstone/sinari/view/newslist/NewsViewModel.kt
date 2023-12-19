package com.capstone.sinari.view.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sinari.data.NewsRepository
import com.capstone.sinari.data.response.NewsItem
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _listItem = MutableLiveData<List<NewsItem>>()
    private val listItem: LiveData<List<NewsItem>> = _listItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getNewsList(referer: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val response = repository.getNews(referer)
            _listItem.value = response
            _isLoading.value = false
        }
    }
}