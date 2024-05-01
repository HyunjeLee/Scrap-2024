package com.scrap.scrap2024

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scrap.scrap2024.databinding.ActivityScrapDetailBinding

class ScrapDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrapDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrapDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아이콘의 original color 구현 위함 // xml 상에서 적용 불가하므로 코드에서 구현
        binding.bottomNavigationView.itemIconTintList = null

        // 뒤로가기 버튼 기능 구현
        binding.buttonBack.setOnClickListener { finish() }

        // 대표이미지 클릭 시 해당 스크랩 링크로 이동
        binding.buttonThumbnail.setOnClickListener {
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

        // TODO: 동작 테스트를 위한 URL 설정 // #18 머지 후 삭제 필요
        binding.textLink.text = "https://github.com/HyunjeLee/Scrap-2024/pull/25"
        // TODO: 동작 테스트를 위한 memo 설정 // #18 머지 후 삭제 필요
        binding.textMemo.text =
            "test\ntesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\ntest\n"

    }
}