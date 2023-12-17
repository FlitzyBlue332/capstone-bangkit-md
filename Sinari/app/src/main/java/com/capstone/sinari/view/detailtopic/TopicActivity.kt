package com.capstone.sinari.view.detailtopic

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sinari.databinding.ActivityTopicBinding
import com.capstone.sinari.view.ViewModelFactory

class TopicActivity : AppCompatActivity() {
    private val viewModel by viewModels<DetailTopicViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityTopicBinding

    companion object {
        const val EXTRA_REFERER = "extra_referer"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get Topic Object
        val topic = intent.getStringExtra(EXTRA_REFERER)

    }
    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

}