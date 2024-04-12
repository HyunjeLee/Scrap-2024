package com.scrap.scrap2024

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.scrap.scrap2024.databinding.ActivityAddCategoryBinding

class AddCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // layout inflate
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val clickCancelListener = View.OnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        val clickAddListener = View.OnClickListener {
            val intentCategory = Intent()
            val title = binding.editTextAddCategory.text.toString()
            intentCategory.putExtra("title", title)
            setResult(Activity.RESULT_OK, intentCategory)
            finish()
        }

        // 카테고리 추가 취소 시
        binding.buttonCancel.setOnClickListener(clickCancelListener)
        binding.buttonBack.setOnClickListener(clickCancelListener)

        // 카테고리 추가 시
        binding.buttonAdd.setOnClickListener(clickAddListener)

    }
}