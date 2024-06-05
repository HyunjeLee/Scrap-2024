package com.scrap.scrap2024.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scrap.scrap2024.databinding.ActivityScrapDetailEditMemoBinding

class ScrapDetailEditMemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrapDetailEditMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrapDetailEditMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로가기 버튼
        binding.buttonBack.setOnClickListener { finish() }
        binding.buttonCancel.setOnClickListener { finish() }

        // 수정하기 버튼 // 추후 api 연결 후 구현

        // 기존의 메모 출력
        binding.editEditMemo.setText(intent.getStringExtra("memo"))
    }
}