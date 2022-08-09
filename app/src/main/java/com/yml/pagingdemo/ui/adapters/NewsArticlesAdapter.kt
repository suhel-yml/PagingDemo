package com.yml.pagingdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yml.pagingdemo.R
import com.yml.pagingdemo.databinding.ItemHeaderBinding
import com.yml.pagingdemo.databinding.ItemNewsBinding
import com.yml.pagingdemo.model.NewsArticleModelComparator
import com.yml.pagingdemo.ui.model.NewsArticleUIModel

class NewsArticlesAdapter(
    private val onClick: (NewsArticleUIModel.Data) -> Unit,
) : PagingDataAdapter<NewsArticleUIModel, RecyclerView.ViewHolder>(
    NewsArticleModelComparator
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_news -> ItemNewsArticleViewHolder(parent)
            R.layout.item_header -> ItemHeaderViewHolder(parent)

            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        if (holder is ItemNewsArticleViewHolder) {
            holder.bind(item as NewsArticleUIModel.Data)
        } else if (holder is ItemHeaderViewHolder) {
            holder.bind(item as NewsArticleUIModel.Header)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (peek(position)) {
            is NewsArticleUIModel.Data -> R.layout.item_news
            is NewsArticleUIModel.Header -> R.layout.item_header

            null -> throw IllegalArgumentException("Invalid position")
        }
    }

    inner class ItemNewsArticleViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
    ) {

        private val binding = ItemNewsBinding.bind(itemView)
        private val tvSource = binding.tvSource
        private val tvName = binding.tvName

        init {
            binding.root.setOnClickListener {
                (getItem(bindingAdapterPosition) as? NewsArticleUIModel.Data)?.let { item ->
                    onClick(item)
                }
            }
        }

        fun bind(item: NewsArticleUIModel.Data) {
            tvSource.text = item.news.source
            tvName.text = item.news.title
        }

    }

    inner class ItemHeaderViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_header, parent, false)
    ) {

        private val binding = ItemHeaderBinding.bind(itemView)
        private val tvDate = binding.tvDate

        fun bind(item: NewsArticleUIModel.Header) {
            tvDate.text = item.dateString
        }

    }

}