package com.capstone.sinari.view.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sinari.databinding.ActivityFavoriteBinding
import com.capstone.sinari.databinding.ItemListNewsBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jenisBerita = intent.getStringExtra("JENIS_BERITA")


        val itemBinding = ItemListNewsBinding.inflate(layoutInflater)

        binding.rvFavoriteNews.addView(itemBinding.root)
    }
}
