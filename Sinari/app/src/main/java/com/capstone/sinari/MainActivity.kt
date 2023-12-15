package com.capstone.sinari

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.sinari.activity.SplashScreenActivity
import com.capstone.sinari.activity.TopicActivity
import com.capstone.sinari.data.model.NewsAdapter
import com.capstone.sinari.data.model.NewsItem
import com.capstone.sinari.data.model.TopicAdapter
import com.capstone.sinari.data.model.TopicItem

class MainActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter
    private lateinit var topicAdapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Intent untuk Splash Screen Activity
        val splashScreenIntent = Intent(this, SplashScreenActivity::class.java)
        startActivity(splashScreenIntent)

        // Inisialisasi RecyclerView untuk berita
        val rvNews: RecyclerView = findViewById(R.id.rv_news)
        rvNews.layoutManager = LinearLayoutManager(this)

        // Dummy data untuk berita
        val newsList = generateDummyNews()
        newsAdapter = NewsAdapter(newsList) { newsItem ->
            // Ketika item berita ditekan, buka TopicActivity
            val topicIntent = Intent(this@MainActivity, TopicActivity::class.java)
            startActivity(topicIntent)
        }
        rvNews.adapter = newsAdapter

        // Inisialisasi RecyclerView untuk topik
        val rvTopic: RecyclerView = findViewById(R.id.rv_topic)
        rvTopic.layoutManager = LinearLayoutManager(this)

        // Dummy data untuk topik
        val topicList = generateDummyTopics()
        topicAdapter = TopicAdapter(topicList) { topicItem ->
            // Ketika item topik ditekan, tampilkan berita sesuai topik
            showNewsForTopic(topicItem)
        }
        rvTopic.adapter = topicAdapter
    }

    private fun showNewsForTopic(topicItem: TopicItem) {
        // Implementasikan logika untuk menampilkan berita sesuai topik
        // ...
    }

    private fun generateDummyNews(): List<NewsItem> {
        // Implementasikan logika untuk menghasilkan data berita
        // ...
        val dummyNewsList = mutableListOf<NewsItem>()
        // Add dummy news items to the list
        return dummyNewsList
    }

    private fun generateDummyTopics(): List<TopicItem> {
        // Implementasikan logika untuk menghasilkan data topik
        // ...
        val dummyTopicList = mutableListOf<TopicItem>()
        // Add dummy topic items to the list
        return dummyTopicList
    }
}