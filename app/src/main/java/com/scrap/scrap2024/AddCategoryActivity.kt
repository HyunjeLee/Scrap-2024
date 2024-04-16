package com.scrap.scrap2024

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.scrap.scrap2024.databinding.ActivityAddCategoryBinding


class AddCategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCategoryBinding

    val clickCancelListener = View.OnClickListener {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
    val clickAddListener = View.OnClickListener {
        // 에러 토스트 메시지 출력
        if (binding.editTextAddCategory.text.isNullOrEmpty()) {
            errorToast(getString(R.string.error_text_length_0))
        } else if (binding.editTextAddCategory.text.length > 21) {
            errorToast(getString(R.string.error_text_length_22))

        } else {
            // 카테고리명 전달 후 액티비티 종료
            val intentCategory = Intent()
            val title = binding.editTextAddCategory.text.toString()
            intentCategory.putExtra("title", title)
            setResult(Activity.RESULT_OK, intentCategory)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 레이아웃 inflate
        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 카테고리 추가 취소 시
        binding.buttonCancel.setOnClickListener(clickCancelListener)
        binding.buttonBack.setOnClickListener(clickCancelListener)

        // 카테고리 추가 시
        binding.buttonAdd.setOnClickListener(clickAddListener)

        // editText의 상태에 따른 추가하기 버튼 활성화 함수
        binding.editTextAddCategory.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateButtonState(s)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun updateButtonState(text: CharSequence?) {
        if (text.isNullOrEmpty() || text.length > 21) {
            // 버튼 비활성화 // edittext가 비어있거나 21글자 초과 시
            binding.buttonAdd.backgroundTintList =
                ColorStateList.valueOf(getColor(R.color.button_deactivated))
            binding.buttonAdd.setTextColor(getColor(R.color.black))
        } else {
            // 버튼 활성화
            binding.buttonAdd.backgroundTintList =
                ColorStateList.valueOf(getColor(R.color.main_heavy))
            binding.buttonAdd.setTextColor(getColor(R.color.white))
        }
    }

    // 커스텀 토스트 메시지
    @Suppress("DEPRECATION")
    @SuppressLint("InflateParams")
    private fun errorToast(message: String) {
        val toast = Toast(this@AddCategoryActivity)
        val toastView: View =
            LayoutInflater.from(this@AddCategoryActivity).inflate(R.layout.toast_text, null)
        toastView.findViewById<TextView>(R.id.textToast).text = message
        toast.view = toastView
        toast.duration = Toast.LENGTH_SHORT
        toast.setGravity(Gravity.BOTTOM, 0, 350)
        toast.show()
    }

}