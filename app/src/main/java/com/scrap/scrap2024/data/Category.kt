package com.scrap.scrap2024.data

data class Category(
    val title: String,
    val count: Int
)

val categoryList = mutableListOf(
    Category("분류되지 않음", 9999),
    Category("아이템 1", 11),
    Category("아이템 2", 22),
    Category("아이템 3", 33),
)
