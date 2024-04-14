package com.scrap.scrap2024.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.scrap.scrap2024.adapter.ScrapAdapter
import com.scrap.scrap2024.data.scrapList
import com.scrap.scrap2024.databinding.FragmentScrapBinding



class ScrapFragment : Fragment() {

    private lateinit var binding: FragmentScrapBinding

    private val scrapAdapter: ScrapAdapter by lazy { ScrapAdapter(scrapList) }
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
        binding.recyclerViewScrap.adapter = scrapAdapter

        return binding.root
    }

}