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

    // 레이아웃 관련 변수
    private val binding by lazy { FragmentScrapBinding.inflate(layoutInflater) }
    private var linearLayoutManager = LinearLayoutManager(context)
    private var gridLayoutManager = GridLayoutManager(context, 2)

    // 어댑터 변수
    private var scrapListAdapter: ScrapListAdapter = ScrapListAdapter(scrapList)
    private var scrapGridAdapter: ScrapGridAdapter = ScrapGridAdapter(scrapList)

    // enum 변수
    private lateinit var viewType: ViewType
    private lateinit var sortType: SortType // TODO: spinner로 변경 필수!! // 현행은 코드 구조 일관성 하락
    private lateinit var orderType: OrderType

    // 상태 변수
    private var editableState: Boolean = false

    // 시스템 변수
    private val imm by lazy { requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private lateinit var customOnBackPressedCallback: CustomOnBackPressedCallback


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // 뒤로가기 콜백 추가
        customOnBackPressedCallback = CustomOnBackPressedCallback(binding)
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            customOnBackPressedCallback
        )

        // 저장된 sharedPreference 초기화
        initializeViewType()
        initializeSortType()
        initializeOrderType()


        // 스크랩 추가 버튼 클릭 시   // 스크랩 추가 화면으로 이동
        binding.fabAddScrap.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    AddScrapActivity::class.java
                )
            )
        }

        // recyclerview 스크롤 시 fabUp 표시 여부
        binding.recyclerViewScrap.addOnScrollListener(customScrollListener)
        // fabUp 클릭 시 맨 위로 스크롤 이동
        binding.fabUp.setOnClickListener { binding.recyclerViewScrap.smoothScrollToPosition(0) }

        // 수정 버튼 클릭 시
        binding.buttonEdit.setOnClickListener { activateEditMode() }
        // 수정 중 체크 버튼 클릭 시  // 수정 완료
        binding.buttonEditCheck.setOnClickListener { confirmEditMode() }
        // 삭제 버튼 클릭 시
        binding.buttonDelete.setOnClickListener { showDeleteDialog() }

        // viewType 버튼 토글
        binding.buttonViewType.setOnClickListener { toggleViewType() }
        // order 버튼 토글
        binding.buttonOrder.setOnClickListener { toggleOrderType() }
        // sort // 날짜 순 정렬
        binding.textSortByDate.setOnClickListener { toggleSortType(SortType.SCRAP_DATE) }
        // sort // 제목 순 정렬
        binding.textSortByTitle.setOnClickListener { toggleSortType(SortType.TITLE) }
        // sort // 드롭다운 메뉴 표시
        binding.textSort.setOnClickListener { showSortMenu() }


        return binding.root
    }


    private fun initializeViewType() {
        viewType = ViewTypePreferenceManager.getInitialType(requireContext())
        setRecyclerView(viewType)
    }

    private fun initializeSortType() {  // spinner로 변경
        sortType = SortTypePreferenceManager.getInitialType(requireContext())
        when (sortType) {   // 1번째 인자는 선택된 아이템 // 2번째 이후 인자는 선택되지 않은 아이템
            SortType.SCRAP_DATE -> setSortMenu(binding.textSortByDate, binding.textSortByTitle)
            SortType.TITLE -> setSortMenu(binding.textSortByTitle, binding.textSortByDate)
        }
    }

    private fun initializeOrderType() {
        // api 추가 필요
        orderType = OrderTypePreferenceManager.getInitialType(requireContext())
        binding.buttonOrder.setImageResource(
            when (orderType) {
                OrderType.ASC -> R.drawable.sort_ascending
                OrderType.DESC -> R.drawable.sort_descending
            }
        )
    }

    private fun toggleViewType() {
        viewType = if (viewType == ViewType.LIST) ViewType.GRID else ViewType.LIST
        setRecyclerView(viewType)
        ViewTypePreferenceManager.saveType(requireContext(), viewType)
    }

    private fun toggleOrderType() {
        orderType = if (orderType == OrderType.ASC) OrderType.DESC else OrderType.ASC
        binding.buttonOrder.setImageResource(
            if (orderType == OrderType.ASC) R.drawable.sort_ascending else R.drawable.sort_descending
        )
        OrderTypePreferenceManager.saveType(requireContext(), orderType)
    }

    private fun toggleSortType(sortType: SortType) {
        this.sortType = sortType
        when (sortType) {
            SortType.SCRAP_DATE -> setSortMenu(binding.textSortByDate, binding.textSortByTitle)
            SortType.TITLE -> setSortMenu(binding.textSortByTitle, binding.textSortByDate)
        }
        SortTypePreferenceManager.saveType(requireContext(), sortType)
        binding.linearMenu.visibility = View.GONE
    }

    private fun setRecyclerView(viewType: ViewType) {
        when (viewType) {
            ViewType.LIST -> {
                binding.buttonViewType.setImageResource(R.drawable.viewtype_list)
                binding.recyclerViewScrap.layoutManager = linearLayoutManager
                binding.recyclerViewScrap.adapter = scrapListAdapter
            }

            ViewType.GRID -> {
                binding.buttonViewType.setImageResource(R.drawable.viewtype_grid)
                binding.recyclerViewScrap.layoutManager = gridLayoutManager
                binding.recyclerViewScrap.adapter = scrapGridAdapter
            }
        }
    }

    private fun setSortMenu(
        selectedView: TextView,
        vararg unselectedViews: TextView
    ) { // spinner로 변경
        // api 필요?
        // 선택된 TextView 설정
        selectedView.setTextColor(requireContext().getColor(R.color.main_heavy))
        selectedView.setCompoundDrawablesWithIntrinsicBounds(
            null,
            null,
            AppCompatResources.getDrawable(requireContext(), R.drawable.custom_check_11_8dp),
            null
        )
        binding.textSort.text = selectedView.text

        // 선택되지 않은 TextView들 설정
        for (unselectedView in unselectedViews) {
            unselectedView.setTextColor(requireContext().getColor(R.color.black))
            unselectedView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
        }
    }

    private fun showSortMenu() {  // spinner로 변경
        binding.linearMenu.visibility =
            if (binding.linearMenu.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }


    private fun activateEditMode() {
        // 기존 편집/삭제 버튼 숨기기 및 체크 버튼 표시
        binding.buttonEdit.visibility = View.GONE
        binding.buttonDelete.visibility = View.GONE
        binding.buttonEditCheck.visibility = View.VISIBLE

        // 편집 모드 활성화
        switchToEditMode(true)
    }

    private fun confirmEditMode() {
        // TODO: 카테고리명 동일 시 api 요청 X // UI는 동일하게 클릭 가능

        if (editableState) {
            // 기존 편집/삭제 버튼 표시 및 체크 버튼 숨기기
            binding.buttonEdit.visibility = View.VISIBLE
            binding.buttonDelete.visibility = View.VISIBLE
            binding.buttonEditCheck.visibility = View.GONE

            // 변경된 제목 text 변경
            binding.textCategoryTitle.text = binding.editTextCategoryTitle.text.toString()

            // 편집 모드 비활성화
            switchToEditMode(false)
        }
    }

    private fun switchToEditMode(isEditing: Boolean) {
        // 편집 여부에 따라 제목 textview 또는 편집전용제목 textview 표시
        binding.editTextCategoryTitle.visibility = if (isEditing) View.VISIBLE else View.GONE
        binding.textCategoryTitle.visibility = if (isEditing) View.GONE else View.VISIBLE

        if (isEditing) {    // 편집 중인 경우 키보드 표시 및 커서 맨 끝으로 이동  // textWatcher 부착
            binding.editTextCategoryTitle.requestFocus()
            binding.editTextCategoryTitle.setSelection(binding.editTextCategoryTitle.length())
            imm.showSoftInput(binding.editTextCategoryTitle, InputMethodManager.SHOW_IMPLICIT)
            binding.editTextCategoryTitle.addTextChangedListener(editTextWatcher)
        } else {    // 편집 중이 아닌 경우 키보드 숨기기
            imm.hideSoftInputFromWindow(binding.editTextCategoryTitle.windowToken, 0)
        }

        // 뒤로가기 콜백 설정
        customOnBackPressedCallback.isEnabled = isEditing
    }


    private fun showErrorToast(text: CharSequence?) {
        // 메세지 분기
        val errorMessage = when {
            text.isNullOrEmpty() -> getString(R.string.error_text_length_0)
            text.length > 21 -> getString(R.string.error_text_length_22)
            else -> throw IllegalArgumentException("Invalid text: $text")
        }
        // 토스트 메세지 출력
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showDeleteDialog() {
        // 중복 사용 시 class로 따로 뺄 것

        // 다이얼로그 생성
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_delete)
        // 추가 경고 문구 표시
        dialog.findViewById<CheckBox>(R.id.checkboxAlert).visibility = View.VISIBLE
        // 다이얼로그 라운딩 처리
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_round_20dp)

        // 경고 문구 text 출력
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
        // text 바인딩
        dialog.findViewById<TextView>(R.id.textAlert).text = spannableStringDeleteDetail


        // 취소 시
        dialog.findViewById<Button>(R.id.buttonCancel).setOnClickListener { dialog.dismiss() }

        // 삭제 시
        dialog.findViewById<Button>(R.id.buttonDelete).setOnClickListener {

//            if (dialog.findViewById<CheckBox>(R.id.checkboxAlert).isChecked) {
//                // TODO:    현재 스크랩을 모두 '분류되지 않음' 카테고리로 이동 // 추후 구현
//            }

            dialog.dismiss()

            // 카테고리 프래그먼트로 이동
            (requireActivity() as MainActivity).goToCategoryFragment()
        }

        // 다이얼로그 출력
        dialog.show()
    }


    private val customScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            Log.i("_SCROLL_OFFSET", "offset :${recyclerView.computeVerticalScrollOffset()}")
            // 사용자의 스크롤 위치에 따라 fabUp 버튼 표시
            if (recyclerView.computeVerticalScrollOffset() == 0) binding.fabUp.hide() else binding.fabUp.show()
        }
    }
    private val editTextWatcher = object : TextWatcher {
        // 텍스트 변경 시
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // 글자 수에 따라 상태변수 설정
            editableState = p0?.length in 1..21
            // 이미지 설정
            binding.buttonEditCheck.setImageResource(if (editableState) R.drawable.check else R.drawable.check_error)
            // 에러 토스트 메세지 출력
            if (!editableState) showErrorToast(p0)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {}
    }

}
