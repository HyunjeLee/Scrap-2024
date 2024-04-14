package com.scrap.scrap2024.data

data class Scrap(
    val scrapId: Int,
    val title: String,
    val scrapURL: String,
    val imageURL: String,
    val isStar: Boolean,
    val scrapDate: String
)

// 테스트 데이터 // 스크랩
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

private val scrap4 = Scrap(
    scrapId = 4,
    title = "네 번째 스크랩",
    scrapURL = "https://example.com/scrap4",
    imageURL = "https://example.com/image4.jpg",
    isStar = true,
    scrapDate = "2024-04-10"
)

private val scrap5 = Scrap(
    scrapId = 5,
    title = "다섯 번째 스크랩",
    scrapURL = "https://example.com/scrap5",
    imageURL = "https://example.com/image5.jpg",
    isStar = false,
    scrapDate = "2024-04-09"
)

private val scrap6 = Scrap(
    scrapId = 6,
    title = "여섯 번째 스크랩",
    scrapURL = "https://example.com/scrap6",
    imageURL = "https://example.com/image6.jpg",
    isStar = true,
    scrapDate = "2024-04-08"
)

private val scrap7 = Scrap(
    scrapId = 7,
    title = "일곱 번째 스크랩",
    scrapURL = "https://example.com/scrap7",
    imageURL = "https://example.com/image7.jpg",
    isStar = false,
    scrapDate = "2024-04-07"
)

private val scrap8 = Scrap(
    scrapId = 8,
    title = "여덟 번째 스크랩",
    scrapURL = "https://example.com/scrap8",
    imageURL = "https://example.com/image8.jpg",
    isStar = true,
    scrapDate = "2024-04-06"
)

private val scrap9 = Scrap(
    scrapId = 9,
    title = "아홉 번째 스크랩",
    scrapURL = "https://example.com/scrap9",
    imageURL = "https://example.com/image9.jpg",
    isStar = false,
    scrapDate = "2024-04-05"
)

private val scrap10 = Scrap(
    scrapId = 10,
    title = "열 번째 스크랩",
    scrapURL = "https://example.com/scrap10",
    imageURL = "https://example.com/image10.jpg",
    isStar = true,
    scrapDate = "2024-04-04"
)

val scrapList = mutableListOf(
    scrap1,
    scrap2,
    scrap3,
    scrap4,
    scrap5,
    scrap6,
    scrap7,
    scrap8,
    scrap9,
    scrap10
)
