package com.yml.pagingdemo.model

import androidx.recyclerview.widget.DiffUtil
import com.yml.pagingdemo.ui.model.NewsArticleUIModel

object NewsArticleModelComparator : DiffUtil.ItemCallback<NewsArticleUIModel>() {
    override fun areItemsTheSame(
        oldItem: NewsArticleUIModel,
        newItem: NewsArticleUIModel
    ): Boolean {
        val isDataSame = oldItem is NewsArticleUIModel.Data
                && newItem is NewsArticleUIModel.Data
                && oldItem == newItem

        val isHeaderSame = oldItem is NewsArticleUIModel.Header
                && newItem is NewsArticleUIModel.Header
                && oldItem == newItem

        return isDataSame || isHeaderSame
    }

    override fun areContentsTheSame(
        oldItem: NewsArticleUIModel,
        newItem: NewsArticleUIModel
    ): Boolean {
        return oldItem == newItem
    }
}