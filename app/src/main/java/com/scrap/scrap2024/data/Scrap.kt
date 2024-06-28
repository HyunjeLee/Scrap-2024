package com.scrap.scrap2024.data

data class Scrap(
    val scrapId: Int,
    val title: String,
    val scrapURL: String,
    val imageURL: String?,
    val isFavorite: Boolean,
    val scrapDate: String,
    val description: String?,
    val memo: String?
)

// 테스트 데이터 // 스크랩
private val scrap7 = Scrap(
    scrapId = 7,
    title = "solved.ac",
    scrapURL = "https://solved.ac/",
    imageURL = "https://static.solved.ac/og.jpg",
    isFavorite = true,
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
    isFavorite = false,
    scrapDate = "2024-04-06",
    description = "개발 트렌드, Q&amp;A, 탑 개발자들과의 네트워킹까지. 누구나 쉽고 간편하게 성장할 수 있도록. 요즘 개발자들의 필수 커뮤니티",
    memo = ""
)

private val scrap9 = Scrap(
    scrapId = 9,
    title = "아홉 번째 스크랩",
    scrapURL = "https://example.com/scrap9",
    imageURL = "https://example.com/image9.jpg",
    isFavorite = false,
    scrapDate = "2024-04-05",
    description = null,
    memo = null
)

private val scrap10 = Scrap(
    scrapId = 10,
    title = "Android 모바일 앱 개발자 도구 - Android 개발자 &nbsp;|&nbsp; Android Developers",
    scrapURL = "https://developer.android.com/?hl=ko",
    imageURL = "https://developer.android.com/static/images/social/android-developers.png?hl=ko",
    isFavorite = true,
    scrapDate = "2024-04-04",
    description = "모든 Android 기기의 개발자를 위한 최신 앱 개발 도구, 플랫폼 업데이트, 교육, 문서를 확인하세요.",
    memo = "안드로이드 개발 공식 홈페이지"
)

private val scrap20 = Scrap(
    scrapId = 20,
    title = "철새 이동경로에 밀웜 잔뜩 뿌려두면 생기는 일",
    scrapURL = "https://www.youtube.com/watch?v=jaLzLwRjNZk",
    imageURL = "https://i.ytimg.com/vi/jaLzLwRjNZk/maxresdefault.jpg",
    isFavorite = true,
    scrapDate = "2024-06-02",
    description = "외딴섬에 철새 먹이터 만들었더니..? 이거 반응 좋네요 #새 #철새 #이동경로",
    memo = "Check this out later"
)

private val scrap21 = Scrap(
    scrapId = 21,
    title = "유부 감자 [네이버웹툰]",
    scrapURL = "https://naver.me/GxNg56fz",
    imageURL = "https://shared-comic.pstatic.net/thumb/webtoon/822556/thumbnail/thumbnail_IMAG21_46b37b9f-0a73-43e0-982a-1cf5666397c7.jpg",
    isFavorite = false,
    scrapDate = "2024-06-02",
    description = "소개팅 첫날 결혼을 약속한 부부가 있다?!아무것도 모르는 감자와 모든 것을 실천하는 남자가 만났다.달라도 이렇게 다를 수 있을까? 다른 세상에서 사는 남녀가 만나 벌어지는 우당탕탕 인생대소사 결혼이야기! 오늘도 감자와 훈남이 보여주는 행복하고 눈물겨운 유부들의 결혼툰!",
    memo = "Interesting content"
)

private val scrap22 = Scrap(
    scrapId = 22,
    title = "[네이버 지도] 온센 부평점",
    scrapURL = "https://naver.me/5XDIGXNp",
    imageURL = "https://search.pstatic.net/common/?src=https%3A%2F%2Fssl.pstatic.net%2Fstatic%2Fmaps%2Fv5%2Fpc%2Fnaver-map.png&type=f&size=1200x630&quality=80&opt=2",
    isFavorite = true,
    scrapDate = "2024-06-02",
    description = "공간을 검색합니다. 생활을 연결합니다. 장소, 버스, 지하철, 도로 등 모든 공간의 정보를 하나의 검색으로 연결한 새로운 지도를 만나보세요.",
    memo = "Share with friends"
)

private val scrap23 = Scrap(
    scrapId = 23,
    title = "씽크웨이 토체티 301 유무선 저소음 텐키리스 기계식 키보드 : 씽크웨이",
    scrapURL = "https://naver.me/5OcXDSWp",
    imageURL = "https://shop-phinf.pstatic.net/20240502_148/1714613045563MLTbY_JPEG/115748888270079121_385990261.jpg?type=o1000",
    isFavorite = false,
    scrapDate = "2024-06-02",
    description = "[씽크웨이] 더 나은 IT 세상을 위한 생각, 씽크웨이 입니다.",
    memo = "Bookmark for reference"
)

private val scrap24 = Scrap(
    scrapId = 24,
    title = "닌텐도 스위치 베터리 개선 제품... | 당근 중고거래",
    scrapURL = "https://www.daangn.com/articles/777836504",
    imageURL = "https://dnvefa72aowie.cloudfront.net/origin/article/202405/0267d1b73f2147bd1318a681173402bea7aa715a697c82ce5d45b43911208154.jpg?f=webp&q=95&s=1440x1440&t=inside",
    isFavorite = true,
    scrapDate = "2024-06-02",
    description = "닌텐도 스위치 베터리 개선 제품 단품 팝니다. 128G메모리 포함 -다운로드 게임 1.포켓몬 아르세우스...",
    memo = "Review this again"
)

private val scrap25 = Scrap(
    scrapId = 25,
    title = "[주식회사 이음에이치알] | [마감임박/단기꿀알바] 6월7,8,9일 EDM페스티벌 행사 STAFF",
    scrapURL = "https://m.albamon.com/jobs/detail/102918407",
    imageURL = "https://mc.albamon.kr/monimg/msa/images/sns_img/og_23-08-01_alba_respect.png",
    isFavorite = false,
    scrapDate = "2024-06-02",
    description = "클릭하여 상세내용을 확인하세요",
    memo = "Check this out later"
)

private val scrap26 = Scrap(
    scrapId = 26,
    title = "인스타그램 릴스 테스트 데이터",
    scrapURL = "https://www.instagram.com/reel/C47vdPhy3yv/?igsh=a25xdWM0MWhqYmow",
    imageURL = null,
    isFavorite = true,
    scrapDate = "2024-06-02",
    description = "인스타그램 릴스에 대한 테스트 데이터입니다. 제공된 링크를 통해 릴스를 확인할 수 있습니다.",
    memo = "Check Instagram reel for updates"
)

val scrapList = mutableListOf(
    scrap7,
    scrap8,
    scrap9,
    scrap10,
    scrap20,
    scrap21,
    scrap22,
    scrap23,
    scrap24,
    scrap25,
    scrap26
)
