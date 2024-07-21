package com.scrap.scrap2024.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scrap.scrap2024.R
import com.scrap.scrap2024.databinding.ActivitySearchSetCategoryBinding

class SearchSetCategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivitySearchSetCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSetCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewTitleWithBack.textTitle.text = getString(R.string.category)
    }
}