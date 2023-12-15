package com.capstone.sinari.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sinari.MainActivity
import com.capstone.sinari.R
import com.capstone.sinari.databinding.ActivitySplashBinding


class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_SCREEN_TIMEOUT: Long = 3000
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.alpha = 0f
        binding.signupButton.alpha = 0f
        binding.loginButton.animate().alpha(1f).duration = 1500
        binding.signupButton.animate().alpha(1f).duration = 1500

        Handler(Looper.getMainLooper()).postDelayed({
            val intentMainActivity = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intentMainActivity)
            finish()
        }, SPLASH_SCREEN_TIMEOUT)

        binding.loginButton.setOnClickListener {
            // Handle klik tombol login
        }

        binding.signupButton.setOnClickListener {
            // Handle klik tombol signup
        }
    }
}