package com.scrap.scrap2024.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.scrap.scrap2024.CategoryAdapter
import com.scrap.scrap2024.databinding.FragmentCategoryBinding


// 테스트 데이터 // 카테고리
private var categoryList = mutableListOf(
    mutableListOf("아이템 1", "22"),
    mutableListOf("아이템 2", "3"),
    mutableListOf("아이템 3", "44")
)

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 inflate
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val view = binding.root

        // recyclerview 어댑터 연결
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCategory.adapter = CategoryAdapter(categoryList)


        return view
    }

}