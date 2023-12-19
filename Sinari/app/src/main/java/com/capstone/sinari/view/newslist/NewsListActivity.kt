package com.capstone.sinari.view.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.capstone.sinari.R
import com.capstone.sinari.databinding.ActivityNewsListBinding
import com.capstone.sinari.view.ViewModelFactory

class NewsListActivity : AppCompatActivity() {

    private val viewModel by viewModels<NewsViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityNewsListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_news_list)



        // show loading
//        viewModel.getNewsList()
//        viewModel.isLoading.observe(this) {isLoading ->
//            showLoading(isLoading)
//        }
    }
}