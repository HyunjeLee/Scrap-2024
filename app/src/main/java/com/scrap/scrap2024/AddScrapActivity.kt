package com.scrap.scrap2024

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scrap.scrap2024.databinding.ActivityAddScrapBinding

class AddScrapActivity : AppCompatActivity() {
    // TODO: hint -> medium_normal 변경 // 입력 시 원상복귀


    private lateinit var binding: ActivityAddScrapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddScrapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutTitleWithBack.textTitle.text = getString(R.string.add_scrap)

        // 스크랩 추가 취소 시
        binding.buttonCancel.setOnClickListener { finish() }
        binding.layoutTitleWithBack.buttonBack.setOnClickListener { finish() }
        // 스크랩 추가 시
        binding.buttonAdd.setOnClickListener {
            // 추후 api 연결 시 구현
        }

    }
}