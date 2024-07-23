package com.scrap.scrap2024.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.scrap.scrap2024.databinding.ActivityMainBinding
import com.scrap.scrap2024.ui.nav.CategoryFragment
import com.scrap.scrap2024.ui.nav.FavoriteFragment
import com.scrap.scrap2024.ui.nav.MypageFragment
import com.scrap.scrap2024.ui.nav.ScrapFragment
import com.scrap.scrap2024.ui.nav.SearchFragment
import com.scrap.scrap2024.R

private const val TAG_CATEGORY = "CategoryFragment"
private const val TAG_SCRAP = "ScrapFragment"
private const val TAG_FAVORITE = "FavoriteFragment"
private const val TAG_SEARCH = "SearchFragment"
private const val TAG_MYPAGE = "MypageFragment"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val imm by lazy { this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // layout inflate
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setFragment(TAG_CATEGORY, CategoryFragment())

        // 바텀 네비게이션 뷰의 하단 공백 생김 방지
        binding.navigationView.setOnApplyWindowInsetsListener(null)
        // 바텀 네비게이션뷰에서 선택된 아이템에 따라 fragment 표시
        binding.navigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.categoryFragment -> setFragment(TAG_CATEGORY, CategoryFragment())
                R.id.scrapFragment -> setFragment(TAG_SCRAP, ScrapFragment())
                R.id.favoriteFragment -> setFragment(TAG_FAVORITE, FavoriteFragment())
                R.id.searchFragment -> setFragment(TAG_SEARCH, SearchFragment())
                R.id.mypageFragment -> setFragment(TAG_MYPAGE, MypageFragment())
            }
            true
        }
    }

    // 인자로 받아온 프래그먼트를 표시하고 나머지 프래그먼트는 숨기는 함수
    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        // 처음 실행 시 트랜잭션에 tag 추가
        if (manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val category = manager.findFragmentByTag(TAG_CATEGORY)
        val scrap = manager.findFragmentByTag(TAG_SCRAP)
        val favorite = manager.findFragmentByTag(TAG_FAVORITE)
        val search = manager.findFragmentByTag(TAG_SEARCH)
        val mypage = manager.findFragmentByTag(TAG_MYPAGE)

        // 초기화 // 전부 숨기기
        if (category != null) {
            fragTransaction.hide(category)
        }
        if (scrap != null) {
            fragTransaction.hide(scrap)
        }
        if (favorite != null) {
            fragTransaction.hide(favorite)
        }
        if (search != null) {
            fragTransaction.hide(search)
        }
        if (mypage != null) {
            fragTransaction.hide(mypage)
        }

        // tag 값에 따라 해당 프래그먼트 표시
        if (tag == TAG_CATEGORY) {
            if (category != null) {
                fragTransaction.show(category)
            }
        } else if (tag == TAG_SCRAP) {
            if (scrap != null) {
                fragTransaction.show(scrap)
            }
        } else if (tag == TAG_FAVORITE) {
            if (favorite != null) {
                fragTransaction.show(favorite)
            }
        } else if (tag == TAG_SEARCH) {
            if (search != null) {
                fragTransaction.show(search)
            }
        } else if (tag == TAG_MYPAGE) {
            if (mypage != null) {
                fragTransaction.show(mypage)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }

    // TODO: 코드 리뷰 필요
    fun goToCategoryFragment() {
        binding.navigationView.menu.findItem(R.id.categoryFragment).isChecked = true
        setFragment(TAG_CATEGORY, CategoryFragment())
    }
}