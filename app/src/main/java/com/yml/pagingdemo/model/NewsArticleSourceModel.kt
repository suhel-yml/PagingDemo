package com.yml.pagingdemo.model

import com.google.gson.annotations.SerializedName

data class NewsArticleSourceModel(
    @SerializedName("name")
    val name: String
)
