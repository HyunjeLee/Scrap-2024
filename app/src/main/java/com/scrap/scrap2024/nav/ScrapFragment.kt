package com.scrap.scrap2024.nav

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scrap.scrap2024.R
import com.scrap.scrap2024.adapter.ScrapAdapter
import com.scrap.scrap2024.data.scrapList
import com.scrap.scrap2024.databinding.FragmentScrapBinding


class ScrapFragment : Fragment() {

    private lateinit var binding: FragmentScrapBinding
    private var scrapAdapter: ScrapAdapter = ScrapAdapter(scrapList)
    private var isAscending: Boolean = true
    private var editState: Boolean = false
    private val imm by lazy { requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃 inflate
        binding = FragmentScrapBinding.inflate(inflater, container, false)


        binding.recyclerViewScrap.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewScrap.adapter = scrapAdapter
// TODO:        binding.recyclerViewScrap.addItemDecoration(GridSpacingItemDecoration(requireContext()))

        // recyclerview 스크롤 시 fabUp 표시 여부
        binding.recyclerViewScrap.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                Log.i("_SCROLL_OFFSET", "offset :${recyclerView.computeVerticalScrollOffset()}")
                if (recyclerView.computeVerticalScrollOffset() == 0) {
                    binding.fabUp.hide()
                } else {
                    binding.fabUp.show()
                }
            }
        })
        // fabUp 클릭 시 맨 위로 스크롤 이동
        binding.fabUp.setOnClickListener {
            binding.recyclerViewScrap.smoothScrollToPosition(0)
        }

        // 정렬 버튼 클릭 시
        binding.buttonSort.setOnClickListener {
            if (isAscending) {
                isAscending = false
                binding.buttonSort.setImageResource(R.drawable.sort_descending)
                // 추후 api 연결
            } else {
                isAscending = true
                binding.buttonSort.setImageResource(R.drawable.sort_ascending)
                // 추후 api 연결
            }
        }

        // 수정 버튼 클릭 시
        binding.buttonEdit.setOnClickListener {
            // 카테고리명 수정 활성화
            activateEdit()

            binding.buttonEdit.visibility = View.GONE
            binding.buttonDelete.visibility = View.GONE
            binding.buttonEditCheck.visibility = View.VISIBLE
        }

        // editTextCategoryTitle의 글자 수에 따른 editState 변경
        binding.editTextCategoryTitle.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (p0.isNullOrEmpty()) {
                    binding.buttonEditCheck.setImageResource(R.drawable.check_error)

                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_text_length_0),
                        Toast.LENGTH_SHORT
                    ).show()

                    editState = false
                } else if (p0.length > 21) {
                    binding.buttonEditCheck.setImageResource(R.drawable.check_error)

                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error_text_length_22),
                        Toast.LENGTH_SHORT
                    ).show()

                    editState = false
                } else {
                    binding.buttonEditCheck.setImageResource(R.drawable.check)

                    editState = true
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}

        })

        // 수정 완료 시
        binding.buttonEditCheck.setOnClickListener {
            if (editState) {

                binding.buttonEdit.visibility = View.VISIBLE
                binding.buttonDelete.visibility = View.VISIBLE
                binding.buttonEditCheck.visibility = View.GONE

                // textview switch & pass
                binding.textCategoryTitle.text = binding.editTextCategoryTitle.text.toString()
                binding.editTextCategoryTitle.visibility = View.GONE
                binding.textCategoryTitle.visibility = View.VISIBLE

                // 키보드 내리기
                imm.hideSoftInputFromWindow(binding.editTextCategoryTitle.windowToken, 0)

                // 카테고리명 수정
                // TODO: 추후 api 연결 후 구현
            }
        }

        return binding.root
    }

    private fun activateEdit() {

        // editState 초기화
        editState = binding.editTextCategoryTitle.length() in 1..21

        // textview switch
        binding.editTextCategoryTitle.visibility = View.VISIBLE
        binding.textCategoryTitle.visibility = View.GONE

        // 커서 표시
        binding.editTextCategoryTitle.requestFocus()
        // 텍스트의 맨 끝으로 커서 위치 변경
        binding.editTextCategoryTitle.setSelection(binding.editTextCategoryTitle.length())

        // 키보드 표시
        imm.showSoftInput(binding.editTextCategoryTitle, InputMethodManager.SHOW_IMPLICIT)
    }

}
