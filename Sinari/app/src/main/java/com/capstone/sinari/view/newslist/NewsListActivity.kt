package com.capstone.sinari.view.newslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.sinari.R
import com.capstone.sinari.data.response.NewsItem
import com.capstone.sinari.databinding.ActivityNewsListBinding
import com.capstone.sinari.view.ViewModelFactory

class NewsListActivity : AppCompatActivity() {

    private val viewModel by viewModels<NewsViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityNewsListBinding
    companion object {
        const val EXTRA_REFERER = "extra_referer"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val referer = intent.getStringExtra(EXTRA_REFERER)

        // show loading
        viewModel.getNewsList(referer!!)
        viewModel.isLoading.observe(this) {isLoading ->
            showLoading(isLoading)
        }

        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvNews.layoutManager = layoutManager
        binding.rvNews.addItemDecoration(itemDecoration)

        // set item list
        viewModel.listItem.observe(this){listItem ->
            setNewsListData(listItem)
        }
//        binding.topAppBar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
//        binding.topAppBar.setNavigationOnClickListener(View.OnClickListener {
//            onBackPressedDispatcher.onBackPressed()
//        })

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setNewsListData(listItem: List<NewsItem>) {
        val adapter = NewsAdapter()
        Log.d("check_news_list", "${listItem.size}")
        adapter.submitList(listItem)
        binding.rvNews.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.progressBarNews.visibility = View.VISIBLE
        }
        else {
            binding.progressBarNews.visibility = View.GONE
        }
    }
}