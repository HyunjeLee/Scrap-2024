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

        // 아이콘의 original color 구현 위함 // xml 상에서 적용 불가하므로 코드에서 구현
        binding.bottomNavigationView.itemIconTintList = null
        // 뒤로가기 버튼 기능 구현
        binding.buttonBack.setOnClickListener { finish() }
    }
}