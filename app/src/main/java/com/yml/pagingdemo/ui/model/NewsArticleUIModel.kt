package com.yml.pagingdemo.ui.model

import com.yml.pagingdemo.model.NewsArticleModel

sealed class NewsArticleUIModel {

    data class Header(val dateString: String) : NewsArticleUIModel()
    data class Data(val news: NewsArticleModel) : NewsArticleUIModel()

}
