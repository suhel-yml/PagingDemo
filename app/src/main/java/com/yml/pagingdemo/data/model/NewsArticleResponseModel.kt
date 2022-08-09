package com.yml.pagingdemo.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsArticlesResponseModel(
    @SerializedName("status")
    val status: String,

    @SerializedName("articles")
    val articles: List<NewsArticleResponseModel>,
)

data class NewsArticleResponseModel(
    @SerializedName("source")
    val source: NewsArticleSourceResponseModel,

    @SerializedName("title")
    val title: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("publishedAt")
    val publishedAt: Date,
)

data class NewsArticleSourceResponseModel(
    @SerializedName("name")
    val name: String
)