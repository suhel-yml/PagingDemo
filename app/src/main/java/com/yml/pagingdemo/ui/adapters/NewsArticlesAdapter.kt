package com.yml.pagingdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yml.pagingdemo.R
import com.yml.pagingdemo.databinding.ItemNewsBinding
import com.yml.pagingdemo.model.NewsArticleModel
import com.yml.pagingdemo.model.NewsArticleModelComparator

class NewsArticlesAdapter :
    PagingDataAdapter<NewsArticleModel, NewsArticlesAdapter.ItemNewsArticleViewHolder>(
        NewsArticleModelComparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNewsArticleViewHolder {
        return ItemNewsArticleViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemNewsArticleViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }

    class ItemNewsArticleViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
    ) {

        private val binding = ItemNewsBinding.bind(itemView)
        private val tvSource = binding.tvSource
        private val tvName = binding.tvName
        private val tvDescription = binding.tvDescription

        fun bind(item: NewsArticleModel) {
            tvSource.text = item.source.name
            tvName.text = item.title
            tvDescription.text = item.description
        }

    }

}