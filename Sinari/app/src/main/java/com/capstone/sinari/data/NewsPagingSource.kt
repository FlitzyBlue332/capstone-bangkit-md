package com.capstone.sinari.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.capstone.sinari.data.response.TopicItem
import com.capstone.sinari.data.retrofit.ApiService

class NewsPagingSource(private val apiService: ApiService)  : PagingSource<Int, TopicItem>() {

    private val exceptionMessage = MutableLiveData<String?>()
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopicItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getTopic(position, params.loadSize)
            Log.d("NewsPagingSource", "response: ${responseData}")

            LoadResult.Page(
                data = responseData.headlines!!,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData == null) null else position + 1
            )
        } catch (exception: Exception) {
            Log.e("NewsPagingSource", "Error: ${exception.message}")
            exceptionMessage.postValue(exception.message)
            return LoadResult.Error(exception)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, TopicItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    fun getExceptionMessage(): LiveData<String?> {
        return exceptionMessage
    }



}