package com.capstone.sinari.view.detailnews

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.sinari.R
import com.capstone.sinari.data.response.NewsItem
import com.capstone.sinari.view.favorite.FavoriteActivity
import com.capstone.sinari.databinding.ActivityBiasBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF

class BiasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBiasBinding

    companion object {
        const val EXTRA_NEWS = "extra_news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get News Object
        val news: NewsItem? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_NEWS, NewsItem::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_NEWS)
        }

        setupView(news!!)
        setupAction(news)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupView(newsItem: NewsItem) {
        binding.title.text = newsItem.title
        binding.description.text = newsItem.excerpt
        Glide.with(this)
            .load(newsItem.thumbnail)
            .into(binding.ivImage)

        setupPieChart(newsItem)
        setupBarChart(newsItem)
    }

    private fun setupAction(newsItem: NewsItem){
        binding.btSource.setOnClickListener {
            val intentSource = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.url))
            startActivity(intentSource)
        }
    }

    private fun setupPieChart(newsItem: NewsItem){
        val left = newsItem.leftTendency * 100
        val neutral = newsItem.centerTendency * 100
        val right = newsItem.rightTendency * 100

        binding.pcNews.setUsePercentValues(true)
        binding.pcNews.setExtraOffsets(5f, 10f, 5f, 5f)
        binding.pcNews.description.isEnabled = false
        binding.pcNews.dragDecelerationFrictionCoef = 0.95f
        binding.pcNews.isDrawHoleEnabled = false
        binding.pcNews.isRotationEnabled = false
        binding.pcNews.legend.isEnabled = false

        // data
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(left)) // left
        entries.add(PieEntry(neutral)) // neutral
        entries.add(PieEntry(right)) // right

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
        colors.add(resources.getColor(R.color.blue, theme))
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.BLACK)
        binding.pcNews.data = data
    }

    private fun setupBarChart(newsItem: NewsItem){
        val subjectivity = newsItem.subjectivity * 100
        val bias = newsItem.bias * 100

        // set data
        val barEntriesList: ArrayList<BarEntry> = ArrayList()
        barEntriesList.add(BarEntry(2f, subjectivity)) // subjektivitas
        barEntriesList.add(BarEntry(1f, bias)) // bias
        val barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")
        val barData = BarData(barDataSet)
        binding.hbcNews.data = barData



        // set up barchart
        binding.hbcNews.xAxis.setDrawLabels(false)
        binding.hbcNews.axisLeft.axisMinimum = 0f
        binding.hbcNews.axisLeft.axisMaximum = 100f


        // color
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.red, theme))
        colors.add(resources.getColor(R.color.purple, theme))
        barDataSet.colors = colors


        barDataSet.valueTextSize = 16f
        binding.description.isEnabled = false
        binding.hbcNews.legend.isEnabled = false
    }
}
