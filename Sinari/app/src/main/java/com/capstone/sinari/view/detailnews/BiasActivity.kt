package com.capstone.sinari.view.detailnews

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sinari.view.favorite.FavoriteActivity
import com.capstone.sinari.databinding.ActivityBiasBinding

class BiasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiasBinding

    companion object {
        const val EXTRA_NEWS = "extra_news"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // implementasi onClickListener untuk btnFavorite
        binding.btnFavorite.setOnClickListener {
            val jenisBerita = "Berita Favorit"
            val intent = Intent(this@BiasActivity, FavoriteActivity::class.java).apply {
                putExtra("JENIS_BERITA", jenisBerita)
            }
            startActivity(intent)
        }
    }
}
