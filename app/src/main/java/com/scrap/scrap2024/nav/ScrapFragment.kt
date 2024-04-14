package com.scrap.scrap2024.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scrap.scrap2024.R

// 테스트 데이터 // 스크랩
private data class Scrap(
    val scrapId: Int,
    val title: String,
    val scrapURL: String,
    val imageURL: String,
    val isStar: Boolean,
    val scrapDate: String
)

private val scrap1 = Scrap(
    scrapId = 1,
    title = "첫 번째 스크랩",
    scrapURL = "https://example.com/scrap1",
    imageURL = "https://example.com/image1.jpg",
    isStar = true,
    scrapDate = "2024-04-13"
)

private val scrap2 = Scrap(
    scrapId = 2,
    title = "두 번째 스크랩",
    scrapURL = "https://example.com/scrap2",
    imageURL = "https://example.com/image2.jpg",
    isStar = false,
    scrapDate = "2024-04-12"
)

private val scrap3 = Scrap(
    scrapId = 3,
    title = "세 번째 스크랩",
    scrapURL = "https://example.com/scrap3",
    imageURL = "https://example.com/image3.jpg",
    isStar = false,
    scrapDate = "2024-04-11"
)

//private val scrap4 = Scrap(
//    scrapId = 4,
//    title = "네 번째 스크랩",
//    scrapURL = "https://example.com/scrap4",
//    imageURL = "https://example.com/image4.jpg",
//    isStar = true,
//    scrapDate = "2024-04-10"
//)

private val scrapList = mutableListOf(
    scrap1,
    scrap2,
    scrap3
)

class ScrapFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 inflate
        return inflater.inflate(R.layout.fragment_scrap, container, false)
    }

}