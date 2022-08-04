package com.yml.pagingdemo.model

import com.google.gson.annotations.SerializedName

data class NewsArticlesResponseModel(
    @SerializedName("status")
    val status: String,

    @SerializedName("articles")
    val articles: List<NewsArticleModel>,
)