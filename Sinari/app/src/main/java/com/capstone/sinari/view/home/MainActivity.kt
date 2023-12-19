package com.capstone.sinari.view.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.sinari.R
import com.capstone.sinari.data.response.Topic
import com.capstone.sinari.data.response.TopicItem
import com.capstone.sinari.databinding.ActivityMainBinding
import com.capstone.sinari.view.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // show loading
        viewModel.isLoading.observe(this) {isLoading ->
            showLoading(isLoading)
        }

        // set item list data
        setTopicListData()


        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvTopic.layoutManager = layoutManager
        binding.rvTopic.addItemDecoration(itemDecoration)

    }

    private fun setTopicListData() {
        val adapter = TopicAdapter()
        viewModel.listTopicItem.observe(this) {listTopicItem ->
            if (listTopicItem != null){
                adapter.submitData(lifecycle, listTopicItem)
            }
            Log.d("MainActivity2", "listTopicItem: ${listTopicItem}")
        }
        binding.rvTopic.adapter = adapter
    }

//    private fun dummydata() {
//        val testTopic = Topic(
//            "https://static.wikia.nocookie.net/mrfz/images/1/14/Schwarz.png/revision/latest/scale-to-width-down/1000?cb=20210913141154",
//            "bandung",
//            "publisher",
//            "https://static.wikia.nocookie.net/mrfz/images/1/14/Schwarz.png/revision/latest/scale-to-width-down/1000?cb=20210913141154",
//            "schawrz sayang",
//            "sayangkuh",
//            "https://arknights.fandom.com/wiki/Schwarz"
//        )
//        val testItem = TopicItem("sayang", testTopic)
//        var listItem: List<TopicItem> = listOf(testItem)
//
//        setTopicListData(listItem)
//    }

    private fun showLoading(isLoading: Boolean){
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        }
        else {
            binding.progressBar.visibility = View.GONE
        }
    }
}