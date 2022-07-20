package com.lazday.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lazday.news.databinding.ActivityDetailBinding

class HomeActivity : AppCompatActivity() {

    private val binding = ActivityDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}