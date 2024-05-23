package com.scrap.scrap2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scrap.scrap2024.databinding.ActivityAddScrapBinding

class AddScrapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddScrapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScrapBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}