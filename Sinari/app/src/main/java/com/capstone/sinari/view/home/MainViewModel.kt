package com.capstone.sinari.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.sinari.data.NewsRepository
import com.capstone.sinari.data.response.TopicItem
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _listItem = MutableLiveData<List<TopicItem>>()
    val listItem : LiveData<List<TopicItem>> = _listItem

    init {
        getTopicList()
    }
    fun getTopicList() {
        viewModelScope.launch {
            val response = repository.getHeadlines()
            _listItem.value = response
        }
    }
}