package com.capstone.sinari.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sinari.view.home.MainActivity
import com.capstone.sinari.databinding.ActivitySplashBinding


class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_SCREEN_TIMEOUT: Long = 1000
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intentMainActivity = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intentMainActivity)
            finish()
        }, SPLASH_SCREEN_TIMEOUT)

    }
}