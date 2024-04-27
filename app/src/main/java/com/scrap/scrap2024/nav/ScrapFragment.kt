package com.scrap.scrap2024.nav

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scrap.scrap2024.R
import com.scrap.scrap2024.adapter.ScrapAdapter
import com.scrap.scrap2024.data.scrapList
import com.scrap.scrap2024.databinding.FragmentScrapBinding


class ScrapFragment : Fragment() {

    private lateinit var binding: FragmentScrapBinding

    private lateinit var scrapAdapter: ScrapAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃 inflate
        binding = FragmentScrapBinding.inflate(inflater, container, false)


        binding.recyclerViewScrap.layoutManager = GridLayoutManager(context, 2)
        scrapAdapter = ScrapAdapter(scrapList.sortedBy { it.scrapDate }.toMutableList())
        binding.recyclerViewScrap.adapter = scrapAdapter

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
            when (binding.textSortType.text) {
                getString(R.string.sort_by_date) -> {
                    // 제목 순 정렬 스코프
                    binding.textSortType.text = getString(R.string.sort_by_title)
                    scrapAdapter = ScrapAdapter(scrapList.sortedBy { it.title }.toMutableList())
                    binding.recyclerViewScrap.adapter = scrapAdapter
                }

                getString(R.string.sort_by_title) -> {
                    // 스크랩한 날짜 순 정렬 스코프
                    binding.textSortType.text = getString(R.string.sort_by_date)
                    scrapAdapter = ScrapAdapter(scrapList.sortedBy { it.scrapDate }.toMutableList())
                    binding.recyclerViewScrap.adapter = scrapAdapter
                }

                else -> {}
            }
        }

        return binding.root
    }

}