package com.scrap.scrap2024.nav

import android.content.Context
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scrap.scrap2024.AddScrapActivity
import com.scrap.scrap2024.MainActivity
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
    private val callback by lazy {
        // 뒤로가기 기능 커스텀
        object : OnBackPressedCallback(false) {
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
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃 inflate
        binding = FragmentScrapBinding.inflate(inflater, container, false)

        // 뒤로가기 콜백 추가
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        // 그리드뷰 스크랩 표시
        binding.recyclerViewScrap.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewScrap.adapter = scrapAdapter
// TODO:        binding.recyclerViewScrap.addItemDecoration(GridSpacingItemDecoration(requireContext()))

        // 스크랩 추가 버튼 클릭 시
        binding.fabAddScrap.setOnClickListener {
            startActivity(Intent(context, AddScrapActivity::class.java))
        }

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

            binding.buttonEdit.visibility = View.GONE
            binding.buttonDelete.visibility = View.GONE
            binding.buttonEditCheck.visibility = View.VISIBLE

            // 카테고리명 수정 활성화
            activateEdit()
        }

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

        // 삭제 버튼 클릭 시
        binding.buttonDelete.setOnClickListener {
            showDeleteDialog()
        }

        return binding.root
    }

    private fun activateEdit() {

        // editState 초기화
        editState = binding.editTextCategoryTitle.length() in 1..21

        // callback 활성화 // 뒤로가기 기능 커스텀
        callback.isEnabled = true

        // textview switch
        binding.editTextCategoryTitle.visibility = View.VISIBLE
        binding.textCategoryTitle.visibility = View.GONE

        // 커서 표시
        binding.editTextCategoryTitle.requestFocus()
        // 텍스트의 맨 끝으로 커서 위치 변경
        binding.editTextCategoryTitle.setSelection(binding.editTextCategoryTitle.length())

        // 키보드 표시
        imm.showSoftInput(binding.editTextCategoryTitle, InputMethodManager.SHOW_IMPLICIT)


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

    }

    private fun showDeleteDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_delete)

        // 추가 경고 문구 표시
        dialog.findViewById<CheckBox>(R.id.checkboxAlert).visibility = View.VISIBLE

        // 다이얼로그 라운딩 처리
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_round_20dp)

        // 경고 문구 출력
        val textAlert = getString(R.string.alert_delete_category)
        val textAlertDetail = getString(R.string.alert_delete_category_detail)
        val spannableStringDeleteDetail = SpannableString(textAlert + textAlertDetail)
        // textAlertDetail의 색상 및 크기 설정
        spannableStringDeleteDetail.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.caution)),
            textAlert.length,
            textAlert.length + textAlertDetail.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableStringDeleteDetail.setSpan(
            RelativeSizeSpan(0.8f), // 13sp
            textAlert.length,
            textAlert.length + textAlertDetail.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        dialog.findViewById<TextView>(R.id.textAlert).text = spannableStringDeleteDetail

        // 취소 시
        dialog.findViewById<Button>(R.id.buttonCancel).setOnClickListener {
            dialog.dismiss()
        }

        // 삭제 시
        dialog.findViewById<Button>(R.id.buttonDelete).setOnClickListener {

//            if (dialog.findViewById<CheckBox>(R.id.checkboxAlert).isChecked) {
//                // TODO:    현재 스크랩을 모두 '분류되지 않음' 카테고리로 이동 // 추후 구현
//            }

            dialog.dismiss()

            // 카테고리 프래그먼트로 이동
            (requireActivity() as MainActivity).goToCategoryFragment()
        }

        dialog.show()
    }

}
