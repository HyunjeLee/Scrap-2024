package com.scrap.scrap2024.ui

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.scrap.scrap2024.R
import com.scrap.scrap2024.databinding.ActivityScrapDetailBinding
import kotlin.properties.Delegates

class ScrapDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrapDetailBinding
    private var isFavorite by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrapDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isFavorite = intent.getBooleanExtra("isFavorite", true)

        // 스크랩 이미지 출력 시 상단의 라운딩 처리 유지 위함
        binding.imageThumbnail.clipToOutline = true

        /*** 이전 화면에서의 변수 바인딩 ***/
        binding.layoutTitleWithBack.textTitle.text = intent.getStringExtra("title")
        binding.textLink.text = intent.getStringExtra("scrapURL")
        // 스크랩 이미지 출력
        Glide.with(applicationContext)
            .load(intent.getStringExtra("imageURL"))
            .into(binding.imageThumbnail)
        // 즐겨찾기 여부에 따른 즐겨찾기 아이콘 변경
        if (isFavorite) {
            binding.bottomNavigationView.menu.findItem(R.id.favoriteIcon)
                .setIcon(R.drawable.favorite)
        }
        if (intent.getStringExtra("description").isNullOrEmpty()) {
            binding.textMainDescription.text = getString(R.string.main_description_default)
            binding.textMainDescription.setTextColor(getColor(R.color.gray))
            // TODO: medium_normal
        } else {
            binding.textMainDescription.text = intent.getStringExtra("description")
        }
        binding.textMemo.text = intent.getStringExtra("memo")
        /*** 이전 화면에서의 변수 바인딩 ***/

        // 아이콘의 original color 구현 위함 // xml 상에서 적용 불가하므로 코드에서 구현
        binding.bottomNavigationView.itemIconTintList = null

        // 뒤로가기 버튼 기능 구현
        binding.layoutTitleWithBack.buttonBack.setOnClickListener { finish() }

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
                    showDeleteDialog()

                    true
                }

                // 메모 수정
                R.id.editIcon -> {
                    val intent =
                        Intent(this@ScrapDetailActivity, ScrapDetailEditMemoActivity::class.java)
                    intent.putExtra("memo", binding.textMemo.text)
                    startActivity(intent)

                    true
                }

                // 즐겨찾기 토글
                R.id.favoriteIcon -> {
                    if (isFavorite) {
                        // api 연결 시 수정 필요
                        isFavorite = false
                        binding.bottomNavigationView.menu.findItem(R.id.favoriteIcon)
                            .setIcon(R.drawable.nav_favorite)
                    } else {
                        isFavorite = true
                        binding.bottomNavigationView.menu.findItem(R.id.favoriteIcon)
                            .setIcon(R.drawable.favorite)
                    }

                    true
                }
                // 공유
                R.id.shareIcon -> {
                    val text =
                        intent.getStringExtra("title") + "\n" + intent.getStringExtra("scrapURL")

                    val intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, text)
                        type = "text/plain"
                    }
                    val shareIntent = Intent.createChooser(intent, null)
                    startActivity(shareIntent)

                    true
                }

                else -> false
            }
        }
    }

    private fun showDeleteDialog() {
        val dialog = Dialog(this@ScrapDetailActivity)
        dialog.setContentView(R.layout.dialog_delete)
        // 다이얼로그 라운딩 처리
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_round_20dp)

        // 경고 문구 출력
        dialog.findViewById<TextView>(R.id.textAlert).text = getString(R.string.alert_delete_scrap)
        // 취소 시
        dialog.findViewById<Button>(R.id.buttonCancel).setOnClickListener {
            dialog.dismiss()
        }
        // 삭제 시
        dialog.findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            dialog.dismiss()
            finish()
        }

        dialog.show()
    }

}