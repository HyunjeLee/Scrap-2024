package com.scrap.scrap2024.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.scrap.scrap2024.R
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
        binding.buttonAdd.setOnClickListener(clickAddListener)

    }

    val clickAddListener = View.OnClickListener {
        if (binding.editTextLink.text.isNullOrEmpty()) {
            Toast.makeText(
                this@AddScrapActivity,
                getString(R.string.error_empty_link),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            // 추후 api 연결 시 구현

        }
    }
}