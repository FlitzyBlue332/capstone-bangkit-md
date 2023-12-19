package com.capstone.sinari.view.detailtopic

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.sinari.R
import com.capstone.sinari.data.response.TopicItem
import com.capstone.sinari.databinding.ActivityTopicBinding
import com.capstone.sinari.view.ViewModelFactory
import com.capstone.sinari.view.newslist.NewsListActivity
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class TopicActivity : AppCompatActivity() {
    private val viewModel by viewModels<DetailTopicViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityTopicBinding

    companion object {
        const val EXTRA_TOPIC = "extra_topic"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get Topic Object
        val topic: TopicItem? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_TOPIC, TopicItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_TOPIC)
        }

        setupView(topic!!)
        setupAction(topic)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupView(topicItem: TopicItem) {
        val topic = topicItem.topic
        binding.judul.text = topic.title
        binding.description.text = topic.excerpt

        Glide.with(this)
            .load(topic.thumbnail)
            .into(binding.imagenews)

        setupPieChart()
        setupBarChart()

    }

    private fun setupAction(topicItem: TopicItem){
        binding.btShowNews.setOnClickListener {
            val intentList = Intent(this, NewsListActivity::class.java)
            intentList.putExtra(NewsListActivity.EXTRA_REFERER, topicItem.referrer)
            startActivity(intentList)
        }
    }

    private fun setupPieChart(){
        binding.pcTopic.setUsePercentValues(true)
        binding.pcTopic.setExtraOffsets(5f, 10f, 5f, 5f)
        binding.pcTopic.description.isEnabled = false
        binding.pcTopic.dragDecelerationFrictionCoef = 0.95f
        binding.pcTopic.isDrawHoleEnabled = false
        binding.pcTopic.isRotationEnabled = false
        binding.pcTopic.legend.isEnabled = false

        // data
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(80f))
        entries.add(PieEntry(10f))
        entries.add(PieEntry(10f))

        // setting pie data set
        val dataSet = PieDataSet(entries, "Neutrality")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f

        // add a colors to list
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.red, theme))
        colors.add(resources.getColor(R.color.white, theme))
        colors.add(resources.getColor(R.color.deep_blue, theme))
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.BLACK)
        binding.pcTopic.data = data
    }

    private fun setupBarChart(){
        // set data
        val barEntriesList: ArrayList<BarEntry> = ArrayList()
        barEntriesList.add(BarEntry(2f, 10f)) // subjektivitas
        barEntriesList.add(BarEntry(1f, 100f)) // bias
        val barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")
        val barData = BarData(barDataSet)


        // set up barchart
        binding.hbcTopic.data = barData

        // color
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.red, theme))
        colors.add(resources.getColor(R.color.purple, theme))
        barDataSet.colors = colors


        barDataSet.valueTextSize = 16f
        binding.description.isEnabled = false
        binding.hbcTopic.legend.isEnabled = false
    }

}