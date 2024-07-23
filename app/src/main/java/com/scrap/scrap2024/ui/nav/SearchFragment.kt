package com.scrap.scrap2024.ui.nav

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.scrap.scrap2024.R
import com.scrap.scrap2024.databinding.FragmentSearchBinding
import com.scrap.scrap2024.ui.SearchSetCategoryActivity

class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }

    private var selectedCategoryTitleArrayList: ArrayList<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 상단 검색 창의 지우기 버튼 클릭 시
        binding.ivIconErase.setOnClickListener { binding.etSearch.text = null }

        // 검색 조건 중 카테고리 추가 버튼 클릭 시  // SR-3 화면으로 이동 // 해당 화면에서 선택된 카테고리명 리스트 수신 후 뷰 생성
        binding.btnAddSearchCategory.setOnClickListener (onClickListenerGotoSearchSetCategoryActivity)



        return binding.root
    }

    private val onClickListenerGotoSearchSetCategoryActivity = OnClickListener {
        searchSetCategoryActivityResultLauncher.launch(
            Intent( requireContext(), SearchSetCategoryActivity::class.java )
        )
    }

    // [SR-1] 검색화면에서 검색 조건 중 카테고리 추가 시 해당 데이터 전달용 launcher
    private val searchSetCategoryActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // 이전 화면에서 받아온 선택된 카테고리명 리스트를 로컬 변수에 저장
                selectedCategoryTitleArrayList = result.data?.getStringArrayListExtra("categoryTitleList")

                // 받아온 리스트를 통해 현재 화면에 뷰 생성
                addSelectedCategoryView(selectedCategoryTitleArrayList)
            }
        }

    private fun addSelectedCategoryView(titleList: ArrayList<String>?) {
        if (titleList.isNullOrEmpty().not()) {
            binding.linearlayoutSearchCategory.removeAllViews()

            for (title in titleList!!) {
                val createdView = LayoutInflater.from(requireContext()).inflate(R.layout.item_category_search_criteria, null)

                // text 설정
                createdView.findViewById<TextView>(R.id.tv_category_title).text = title
                // clear 버튼 클릭 리스너
                createdView.findViewById<Button>(R.id.btn_clear_category).setOnClickListener {
                    (createdView.parent as ViewGroup).removeView(createdView)
                }

                binding.linearlayoutSearchCategory.addView(createdView)
            }
        }
    }

}