package com.scrap.scrap2024.ui.nav

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.scrap.scrap2024.ui.AddCategoryActivity
import com.scrap.scrap2024.adapter.CategoryAdapter
import com.scrap.scrap2024.databinding.FragmentCategoryBinding


// 테스트 데이터 // 카테고리
private var categoryList = mutableListOf(
    mutableListOf("아이템 1", "22"),
    mutableListOf("아이템 2", "3"),
    mutableListOf("아이템 3", "44")
)

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    //    private lateinit var categoryList: MutableList<MutableList<String>>   // 추후 api 연결 시
    private val categoryAdapter: CategoryAdapter by lazy { CategoryAdapter(categoryList) }

    // 카테고리 추가화면에서 추가 시 해당 데이터 전달용 launcher
    private val addCategoryActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val categoryTitle: String? = data?.getStringExtra("title")
                // 전달받은 name을 카테고리뷰에 추가 및 동기화 // "0"은 카테고리 내부 스크랩 개수
                categoryTitle?.let { title ->
                    categoryAdapter.addCategory(mutableListOf(title, "0"))
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃 inflate
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        // recyclerview 어댑터 연결
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCategory.adapter = categoryAdapter

        binding.fabAddCategory.setOnClickListener {
            // 카테고리 추가 화면으로 이동
            addCategoryActivityResultLauncher.launch(
                Intent(
                    context,
                    AddCategoryActivity::class.java
                )
            )
        }

        return view
    }

}