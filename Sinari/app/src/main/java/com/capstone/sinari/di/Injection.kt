package com.capstone.sinari.di

import android.content.Context
import com.capstone.sinari.data.NewsRepository
import com.capstone.sinari.data.pref.UserPreference
import com.capstone.sinari.data.pref.dataStore

object Injection {
    fun provideRepository(context: Context): NewsRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return NewsRepository.getInstance()
    }
}