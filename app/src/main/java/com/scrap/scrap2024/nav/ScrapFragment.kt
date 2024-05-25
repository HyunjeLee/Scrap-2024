package com.scrap.scrap2024.nav

import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scrap.scrap2024.MainActivity
import com.scrap.scrap2024.R
import com.scrap.scrap2024.adapter.ScrapAdapter
import com.scrap.scrap2024.data.scrapList
import com.scrap.scrap2024.databinding.FragmentScrapBinding


class ScrapFragment : Fragment() {

    private lateinit var binding: FragmentScrapBinding
    private var scrapAdapter: ScrapAdapter = ScrapAdapter(scrapList)
    private var isAscending: Boolean = true

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

        // 삭제 버튼 클릭 시
        binding.buttonDelete.setOnClickListener {
            showDeleteDialog()
        }

        return binding.root
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

            if (dialog.findViewById<CheckBox>(R.id.checkboxAlert).isChecked) {
                // TODO:    현재 스크랩을 모두 '분류되지 않음' 카테고리로 이동 // 추후 구현
            }

            dialog.dismiss()

            // 카테고리 프래그먼트로 이동
            (requireActivity() as MainActivity).goToCategoryFragment()
        }

        dialog.show()
    }

}
