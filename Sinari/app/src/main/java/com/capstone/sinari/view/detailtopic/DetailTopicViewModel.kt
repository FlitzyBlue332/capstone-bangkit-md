package com.capstone.sinari.view.detailtopic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sinari.data.NewsRepository
import com.capstone.sinari.data.response.NewsItem
import com.capstone.sinari.data.response.TopicItem
import kotlinx.coroutines.launch

class DetailTopicViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _listItem = MutableLiveData<List<NewsItem>>()
    val listItem : LiveData<List<NewsItem>> = _listItem

    fun getTopicDetail(referer: String) {
        viewModelScope.launch {
            val response = repository.getNews(referer)
            _listItem.value = response
        }
    }
}