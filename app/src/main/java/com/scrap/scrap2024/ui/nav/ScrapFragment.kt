package com.scrap.scrap2024.ui.nav

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
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scrap.scrap2024.ui.AddScrapActivity
import com.scrap.scrap2024.ui.MainActivity
import com.scrap.scrap2024.R
import com.scrap.scrap2024.adapter.ScrapGridAdapter
import com.scrap.scrap2024.adapter.ScrapListAdapter
import com.scrap.scrap2024.callback.CustomOnBackPressedCallback
import com.scrap.scrap2024.data.OrderType
import com.scrap.scrap2024.data.SortType
import com.scrap.scrap2024.data.ViewType
import com.scrap.scrap2024.data.scrapList
import com.scrap.scrap2024.databinding.FragmentScrapBinding
import com.scrap.scrap2024.preferenceManager.OrderTypePreferenceManager
import com.scrap.scrap2024.preferenceManager.SortTypePreferenceManager
import com.scrap.scrap2024.preferenceManager.ViewTypePreferenceManager


class ScrapFragment : Fragment() {
    // TODO: 카테고리명 동일 시 api 요청 X // UI는 동일하게 클릭 가능

    private lateinit var binding: FragmentScrapBinding
    private var linearLayoutManager = LinearLayoutManager(context)
    private var gridLayoutManager = GridLayoutManager(context, 2)
    private var scrapListAdapter: ScrapListAdapter = ScrapListAdapter(scrapList)
    private var scrapGridAdapter: ScrapGridAdapter = ScrapGridAdapter(scrapList)
    private lateinit var viewType: ViewType
    private lateinit var sortType: SortType
    private lateinit var orderType: OrderType
    private var editState: Boolean = false
    private val imm by lazy { requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private lateinit var customOnBackPressedCallback: CustomOnBackPressedCallback


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃 inflate
        binding = FragmentScrapBinding.inflate(inflater, container, false)
        customOnBackPressedCallback = CustomOnBackPressedCallback(binding)

        // 뒤로가기 콜백 추가
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            customOnBackPressedCallback
        )

        // 뷰타입 초기화
        viewType = ViewTypePreferenceManager.getInitialType(requireContext())
        when (viewType) {
            ViewType.LIST -> {  // 첫 실행 // 또는 저장된 뷰타입이 리스트뷰
                binding.buttonViewType.setImageResource(R.drawable.viewtype_list)

                binding.recyclerViewScrap.layoutManager = linearLayoutManager
                binding.recyclerViewScrap.adapter = scrapListAdapter
            }

            ViewType.GRID -> {  // 저장된 뷰타입이 그리드뷰
                binding.buttonViewType.setImageResource(R.drawable.viewtype_grid)

                binding.recyclerViewScrap.layoutManager = gridLayoutManager
                binding.recyclerViewScrap.adapter = scrapGridAdapter
            }
        }

        // sort타입 초기화
        sortType = SortTypePreferenceManager.getInitialType(requireContext())
        when (sortType) {
            SortType.SCRAP_DATE -> {    // 첫 실행 // 또는 저장된 sort타입이 스크랩한 날짜 순
                // api 필요

                // sort의 드롭다운 메뉴 내의 스크랩한 날짜 순 text의 색상 변경 및 체크 표시 추가
                binding.textSortByDate.setTextColor(requireContext().getColor(R.color.main_heavy))
                binding.textSortByDate.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.custom_check_11_8dp
                    ),
                    null
                )
                // 스크랩한 날짜 순 으로 표기
                binding.textSort.text = binding.textSortByDate.text
            }

            SortType.TITLE -> { // 저장된 sort타입이 제목 순
                // api 필요

                // sort의 드롭다운 메뉴 내의 제목 순 text의 색상 변경 및 체크 표시 추가
                binding.textSortByTitle.setTextColor(requireContext().getColor(R.color.main_heavy))
                binding.textSortByTitle.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.custom_check_11_8dp
                    ),
                    null
                )
                // 제목 순 으로 표기
                binding.textSort.text = binding.textSortByTitle.text
            }
        }

        // order타입 초기화
        orderType = OrderTypePreferenceManager.getInitialType(requireContext())
        when (orderType) {
            OrderType.ASC -> {
                // api 필요

                binding.buttonOrder.setImageResource(R.drawable.sort_ascending)
            }

            OrderType.DESC -> {
                // api 필요

                binding.buttonOrder.setImageResource(R.drawable.sort_descending)
            }
        }


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

        // order 버튼 클릭 시
        binding.buttonOrder.setOnClickListener {
            when (orderType) {
                OrderType.ASC -> {  // ASC -> DESC
                    binding.buttonOrder.setImageResource(R.drawable.sort_descending)

                    // order타입 저장
                    orderType = OrderType.DESC
                    OrderTypePreferenceManager.saveType(requireContext(), orderType)
                }

                OrderType.DESC -> { // DESC -> ASC
                    binding.buttonOrder.setImageResource(R.drawable.sort_ascending)

                    // order타입 저장
                    orderType = OrderType.ASC
                    OrderTypePreferenceManager.saveType(requireContext(), orderType)
                }
            }
        }

        // viewType 버튼 클릭 시
        binding.buttonViewType.setOnClickListener {
            when (viewType) {
                ViewType.LIST -> { // 그리드뷰로 변경
                    binding.buttonViewType.setImageResource(R.drawable.viewtype_grid)
                    binding.recyclerViewScrap.layoutManager = gridLayoutManager
                    binding.recyclerViewScrap.adapter = scrapGridAdapter
                    // TODO:        binding.recyclerViewScrap.addItemDecoration(GridSpacingItemDecoration(requireContext()))

                    // 그리드뷰 뷰타입 저장
                    viewType = ViewType.GRID
                    ViewTypePreferenceManager.saveType(requireContext(), viewType)
                }

                ViewType.GRID -> { // 리스트뷰로 변경
                    binding.buttonViewType.setImageResource(R.drawable.viewtype_list)
                    binding.recyclerViewScrap.layoutManager = linearLayoutManager
                    binding.recyclerViewScrap.adapter = scrapListAdapter

                    // 리스트뷰 뷰타입 저장
                    viewType = ViewType.LIST
                    ViewTypePreferenceManager.saveType(requireContext(), viewType)
                }

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

        // sort 버튼 클릭 시 // 드롭다운 메뉴 표시 토글
        binding.textSort.setOnClickListener {
            if (binding.linearMenu.visibility == View.VISIBLE) {
                binding.linearMenu.visibility = View.GONE
            } else {
                binding.linearMenu.visibility = View.VISIBLE
            }
        }
        // 날짜 순 정렬
        binding.textSortByDate.setOnClickListener {
            // api 필요

            // sort의 드롭다운 메뉴 내의 제목 순 text의 색상 변경 및 체크 표시 추가
            binding.textSortByDate.setTextColor(requireContext().getColor(R.color.main_heavy))
            binding.textSortByDate.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_check_11_8dp),
                null
            )
            // 스크랩한 날짜 순 text의 색상 및 체크 표시 원상복귀
            binding.textSortByTitle.setTextColor(requireContext().getColor(R.color.black))
            binding.textSortByTitle.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null
            )

            // 스크랩한 날짜 순 으로 표기
            binding.textSort.text = binding.textSortByDate.text
            // 드롭다운 메뉴 숨기기
            binding.linearMenu.visibility = View.GONE

            // sort타입 저장
            SortTypePreferenceManager.saveType(requireContext(), SortType.SCRAP_DATE)
        }
        // 제목 순 정렬
        binding.textSortByTitle.setOnClickListener {
            // api 필요

            // sort의 드롭다운 메뉴 내의 스크랩한 날짜 순 text의 색상 변경 및 체크 표시 추가
            binding.textSortByTitle.setTextColor(requireContext().getColor(R.color.main_heavy))
            binding.textSortByTitle.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                AppCompatResources.getDrawable(requireContext(), R.drawable.custom_check_11_8dp),
                null
            )
            // 제목 순 text의 색상 및 체크 표시 원상복귀
            binding.textSortByDate.setTextColor(requireContext().getColor(R.color.black))
            binding.textSortByDate.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null
            )

            // 제목 순 으로 표기
            binding.textSort.text = binding.textSortByTitle.text
            // 드롭다운 메뉴 숨기기
            binding.linearMenu.visibility = View.GONE

            // sort타입 저장
            SortTypePreferenceManager.saveType(requireContext(), SortType.TITLE)
        }



        return binding.root
    }

    private fun activateEdit() {

        // editState 초기화
        editState = binding.editTextCategoryTitle.length() in 1..21

        // callback 활성화 // 뒤로가기 기능 커스텀
        customOnBackPressedCallback.isEnabled = true

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
            ForegroundColorSpan(requireContext().getColor(R.color.caution)),
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
