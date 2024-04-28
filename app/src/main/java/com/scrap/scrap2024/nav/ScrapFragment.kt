package com.scrap.scrap2024.nav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scrap.scrap2024.ScrapDetailActivity
import com.scrap.scrap2024.databinding.FragmentScrapBinding


class ScrapFragment : Fragment() {

    private lateinit var binding: FragmentScrapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃 inflate
        binding = FragmentScrapBinding.inflate(inflater, container, false)

        binding.textScrap.setOnClickListener {
            startActivity(Intent(activity, ScrapDetailActivity::class.java))
        }

        return binding.root
    }

}