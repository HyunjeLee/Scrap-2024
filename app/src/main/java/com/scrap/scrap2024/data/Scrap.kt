package com.scrap.scrap2024.data

data class Scrap(
    val scrapId: Int,
    val title: String,
    val scrapURL: String,
    val imageURL: String?,
    val isFavorited: Boolean,
    val scrapDate: String,
    val description: String?,
    val memo: String?
)

// 테스트 데이터 // 스크랩
private val scrap1 = Scrap(
    scrapId = 1,
    title = "첫 번째 스크랩\n\n\n\n\n\ntesting",
    scrapURL = "https://example.com/scrap1",
    imageURL = "https://example.com/image1.jpg",
    isFavorited = true,
    scrapDate = "2024-04-13",
    description = "",
    memo = "테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n테스트\n"
)

private val scrap2 = Scrap(
    scrapId = 2,
    title = "두 번째 스크랩",
    scrapURL = "https://example.com/scrap2",
    imageURL = "https://example.com/image2.jpg",
    isFavorited = false,
    scrapDate = "2024-04-12",
    description = null,
    memo = null
)

private val scrap3 = Scrap(
    scrapId = 3,
    title = "세 번째 스크랩",
    scrapURL = "https://example.com/scrap3",
    imageURL = "https://example.com/image3.jpg",
    isFavorited = false,
    scrapDate = "2024-04-11",
    description = null,
    memo = null
)

private val scrap4 = Scrap(
    scrapId = 4,
    title = "네 번째 스크랩",
    scrapURL = "https://example.com/scrap4",
    imageURL = "https://example.com/image4.jpg",
    isFavorited = true,
    scrapDate = "2024-04-10",
    description = null,
    memo = null
)

private val scrap5 = Scrap(
    scrapId = 5,
    title = "다섯 번째 스크랩",
    scrapURL = "https://example.com/scrap5",
    imageURL = "https://example.com/image5.jpg",
    isFavorited = false,
    scrapDate = "2024-04-09",
    description = null,
    memo = null
)

private val scrap6 = Scrap(
    scrapId = 6,
    title = "여섯 번째 스크랩",
    scrapURL = "https://example.com/scrap6",
    imageURL = "https://example.com/image6.jpg",
    isFavorited = true,
    scrapDate = "2024-04-08",
    description = null,
    memo = null
)

private val scrap7 = Scrap(
    scrapId = 7,
    title = "solved.ac",
    scrapURL = "https://solved.ac/",
    imageURL = "https://static.solved.ac/og.jpg",
    isFavorited = false,
    scrapDate = "2024-04-07",
    description = "알고리즘 문제해결 학습의 이정표 \uD83D\uDEA9 Baekjoon Online Judge 문제들의 난이도 및 티어 정보를 제공하는 사이트입니다.",
    memo = "알고리즘용"
)

private val scrap8 = Scrap(
    scrapId = 8,
    title = "커리어리 | 요즘 개발자 커뮤니티\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "testing",
    scrapURL = "https://careerly.co.kr/home",
    imageURL = "https://careerly.co.kr/_next/static/images/img_meta_engineer-community-7b5078162ebdd783a2bede0d89d85396.png",
    isFavorited = false,
    scrapDate = "2024-04-06",
    description = "개발 트렌드, Q&amp;A, 탑 개발자들과의 네트워킹까지. 누구나 쉽고 간편하게 성장할 수 있도록. 요즘 개발자들의 필수 커뮤니티",
    memo = ""
)

private val scrap9 = Scrap(
    scrapId = 9,
    title = "아홉 번째 스크랩",
    scrapURL = "https://example.com/scrap9",
    imageURL = "https://example.com/image9.jpg",
    isFavorited = false,
    scrapDate = "2024-04-05",
    description = null,
    memo = null
)

private val scrap10 = Scrap(
    scrapId = 10,
    title = "Android 모바일 앱 개발자 도구 - Android 개발자 &nbsp;|&nbsp; Android Developers",
    scrapURL = "https://developer.android.com/?hl=ko",
    imageURL = "https://developer.android.com/static/images/social/android-developers.png?hl=ko",
    isFavorited = true,
    scrapDate = "2024-04-04",
    description = "모든 Android 기기의 개발자를 위한 최신 앱 개발 도구, 플랫폼 업데이트, 교육, 문서를 확인하세요.",
    memo = "안드로이드 개발 공식 홈페이지"
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
