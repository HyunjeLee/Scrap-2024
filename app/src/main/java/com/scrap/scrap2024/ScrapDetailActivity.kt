package com.scrap.scrap2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scrap.scrap2024.databinding.ActivityScrapDetailBinding

class ScrapDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrapDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrapDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}