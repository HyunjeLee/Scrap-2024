package com.scrap.scrap2024

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.scrap.scrap2024.databinding.ActivityScrapDetailBinding

class ScrapDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrapDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrapDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 스크랩 이미지 출력 시 상단의 라운딩 처리 유지 위함
        binding.imageThumbnail.clipToOutline = true

        /*** 이전 화면에서의 변수 바인딩 ***/
        binding.textTitle.text = intent.getStringExtra("title")
        binding.textLink.text = intent.getStringExtra("link")
        // 스크랩 이미지 출력
        Glide.with(applicationContext)
            .load(intent.getStringExtra("imageUrl"))
            .into(binding.imageThumbnail)
        // 즐겨찾기 여부에 따른 즐겨찾기 아이콘 변경
        if (intent.getBooleanExtra("isFavorited", true)) {
            binding.bottomNavigationView.menu.findItem(R.id.favoriteIcon)
                .setIcon(R.drawable.favorite)
        }
        if (intent.getStringExtra("description").isNullOrEmpty()) {
            binding.textMainDescription.text = getString(R.string.main_description_default)
            binding.textMainDescription.setTextColor(getColor(R.color.gray))
        } else {
            binding.textMainDescription.text = intent.getStringExtra("description")
        }
        binding.textMemo.text = intent.getStringExtra("memo")
        /*** 이전 화면에서의 변수 바인딩 ***/

        // 아이콘의 original color 구현 위함 // xml 상에서 적용 불가하므로 코드에서 구현
        binding.bottomNavigationView.itemIconTintList = null

        // 뒤로가기 버튼 기능 구현
        binding.buttonBack.setOnClickListener { finish() }

        // 대표이미지 클릭 시 해당 스크랩 링크로 이동
        binding.imageThumbnail.setOnClickListener {
            val url = binding.textLink.text.toString()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        // 링크 복사 버튼 클릭 시 클립보드에 링크 복사
        binding.buttonCopyLink.setOnClickListener {
            val clipboard: ClipboardManager =
                getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", binding.textLink.text)
            clipboard.setPrimaryClip(clip)
        }

        // 바텀네비게이션뷰의 각각의 아이콘 클릭 시
        clickMenu()

    }

    private fun clickMenu() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                // 삭제
                R.id.deleteIcon -> {
                    true
                }

                // 메모 수정
                R.id.editIcon -> {
                    true
                }

                // 즐겨찾기
                R.id.favoriteIcon -> {
                    true
                }

                else -> false
            }
        }
    }
}