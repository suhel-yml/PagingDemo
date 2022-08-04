package com.yml.pagingdemo.model

import androidx.recyclerview.widget.DiffUtil

object NewsArticleModelComparator : DiffUtil.ItemCallback<NewsArticleModel>() {
    override fun areItemsTheSame(oldItem: NewsArticleModel, newItem: NewsArticleModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NewsArticleModel, newItem: NewsArticleModel): Boolean {
        return oldItem == newItem
    }
}