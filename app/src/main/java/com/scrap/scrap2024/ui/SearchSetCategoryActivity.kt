package com.scrap.scrap2024.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.scrap.scrap2024.R
import com.scrap.scrap2024.adapter.CategorySetAdapter
import com.scrap.scrap2024.data.categoryList
import com.scrap.scrap2024.databinding.ActivitySearchSetCategoryBinding

class SearchSetCategoryActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySearchSetCategoryBinding.inflate(layoutInflater) }
    private val categorySetAdapter = CategorySetAdapter(categoryList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 상단 화면 이름 바인딩
        binding.viewTitleWithBack.textTitle.text = getString(R.string.category)

        // 뒤로가기 기능
        binding.viewTitleWithBack.buttonBack.setOnClickListener(onClickListenerCancel)
        binding.btnCancel.setOnClickListener(onClickListenerCancel)

        // 완료 버튼 클릭 시
        binding.btnComplete.setOnClickListener(onClickListenerComplete)

        // recyclerveiw 초기화
        initRecyclerview()

    }

    private val onClickListenerCancel = View.OnClickListener {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
    private val onClickListenerComplete = View.OnClickListener {
        val intentSearchSetCategory = Intent()
        val selectedCategoryTitleArrayList: ArrayList<String> = ArrayList(categorySetAdapter.selectedCategoryTitleSet.sorted())

        intentSearchSetCategory.putExtra("categoryTitleList", selectedCategoryTitleArrayList)
        setResult(Activity.RESULT_OK, intentSearchSetCategory)
        finish()
    }

    private fun initRecyclerview() {
        // recyclerview 어댑터 연결
        binding.rvSetCategory.layoutManager = LinearLayoutManager(this)
        binding.rvSetCategory.adapter = categorySetAdapter

        // recyclerview의 아이템 간 분할선 추가
        binding.rvSetCategory.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

    }
}