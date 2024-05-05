package com.scrap.scrap2024.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scrap.scrap2024.databinding.FragmentMypageBinding


class MypageFragment : Fragment() {

    private lateinit var binding: FragmentMypageBinding

    // 테스트 데이터 // TODO: api 연결 시 삭제
    private val totalScrap: Int = (0..999).random()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 레이아웃 inflate
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        // totalScrap 출력
        binding.textCount1.text = (totalScrap / 100).toString()
        binding.textCount2.text = ((totalScrap % 100) / 10).toString()
        binding.textCount3.text = (totalScrap % 10).toString()

        // 로그아웃 시 액티비티 종료
        binding.buttonLogout.setOnClickListener { requireActivity().finish() }
        // 회원탈퇴 시 액티비티 종료 // TODO: 추후 api 연결 필요
        binding.buttonWithdrawal.setOnClickListener { requireActivity().finish() }

        return binding.root
    }

}