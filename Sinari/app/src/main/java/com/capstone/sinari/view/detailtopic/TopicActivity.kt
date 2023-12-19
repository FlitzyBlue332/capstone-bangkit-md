package com.capstone.sinari.view.detailtopic

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
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
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

    }

    private fun setupAction(){

    }

    private fun setupPieChart(){
        binding.pcTopic.setUsePercentValues(true)
        binding.pcTopic.setExtraOffsets(5f, 10f, 5f, 5f)
        binding.pcTopic.description.isEnabled = false
        binding.pcTopic.dragDecelerationFrictionCoef = 0.95f
        binding.pcTopic.isDrawHoleEnabled = false
        binding.pcTopic.isRotationEnabled = false

        // data
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(70f))
        entries.add(PieEntry(20f))
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

}