package com.yml.pagingdemo.model

import java.util.*

data class NewsArticleModel(
    val source: String,
    val title: String,
    val description: String,
    val url: String,
    val publishedAt: Date,
)