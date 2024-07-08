package com.scrap.scrap2024.callback

import android.view.View
import androidx.activity.OnBackPressedCallback
import com.scrap.scrap2024.databinding.FragmentScrapBinding

class CustomOnBackPressedCallback(private val binding: FragmentScrapBinding) :
    OnBackPressedCallback(false) {
    override fun handleOnBackPressed() {
        // 편집 모드 비활성화
        binding.buttonEdit.visibility = View.VISIBLE
        binding.buttonDelete.visibility = View.VISIBLE
        binding.buttonEditCheck.visibility = View.GONE

        binding.editTextCategoryTitle.visibility = View.GONE
        binding.textCategoryTitle.visibility = View.VISIBLE

        // 카테고리명 편집내역 날리기
        binding.editTextCategoryTitle.setText(binding.textCategoryTitle.text)

        // callback 비활성화
        this.isEnabled = false
    }
}