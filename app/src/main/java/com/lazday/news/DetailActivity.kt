package com.lazday.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lazday.news.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding = ActivityDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}