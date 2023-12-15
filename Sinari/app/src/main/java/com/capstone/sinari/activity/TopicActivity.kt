package com.capstone.sinari.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sinari.data.model.TopicAdapter
import com.capstone.sinari.databinding.ActivityTopicBinding

class TopicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopicBinding
    private lateinit var adapter: TopicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Implementasikan logika ketika item di RecyclerView diklik
        }

    }