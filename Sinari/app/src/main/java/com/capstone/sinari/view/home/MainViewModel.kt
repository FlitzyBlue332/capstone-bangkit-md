package com.capstone.sinari.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.capstone.sinari.data.NewsRepository
import com.capstone.sinari.data.response.TopicItem
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository) : ViewModel() {
//    private val _listItem = MutableLiveData<List<TopicItem>>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    val listNewsItem: LiveData<PagingData<TopicItem>> = repository.getHeadlinesPager().cachedIn(viewModelScope)

//    init {
//        getTopicList()
//    }
//    fun getTopicList() {
//        _isLoading.value = true
//        viewModelScope.launch {
//            val response = repository.getHeadlines()
//            _listItem.value = response
//            _isLoading.value = false
//        }
//    }
}