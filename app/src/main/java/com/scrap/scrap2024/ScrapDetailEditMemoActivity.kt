package com.scrap.scrap2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scrap.scrap2024.databinding.ActivityScrapDetailEditMemoBinding

class ScrapDetailEditMemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrapDetailEditMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrapDetailEditMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}