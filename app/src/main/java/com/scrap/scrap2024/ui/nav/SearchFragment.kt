package com.scrap.scrap2024.ui.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.scrap.scrap2024.R
import com.scrap.scrap2024.databinding.FragmentSearchBinding
import com.scrap.scrap2024.ui.MainActivity

class SearchFragment : Fragment() {

    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.ivIconErase.setOnClickListener {
            binding.etSearch.text = null
        }

        showSearchedScrap()

        return binding.root
    }

    private fun validationSearchRange(): Boolean {
        // 3개의 checkbox가 전부 비활성화 상태라면 false return
        return !(binding.checkboxTitle.isChecked.not() && binding.checkboxDescription.isChecked.not() && binding.checkboxMemo.isChecked.not())
    }

    private fun showErrorToastSearchRange() {
        Toast.makeText(requireContext(), R.string.error_search_range_at_least_1, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showSearchedScrap() {

        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) { // 검색 완료 버튼 클릭 시
                if (validationSearchRange()) {
                    // 포커스 해제
                    binding.etSearch.clearFocus()
                    // 키보드 내리기
                    (activity as MainActivity).imm.hideSoftInputFromWindow(
                        binding.etSearch.windowToken,
                        0
                    )

                    // TODO: 스크랩 보여주기

                } else {
                    // 토스트메시지 출력
                    showErrorToastSearchRange()
                }

                true
            } else {    // 그외의 버튼 클릭 시
                false
            }
        }

    }


}