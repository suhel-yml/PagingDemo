package com.yml.pagingdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.yml.pagingdemo.data.NewsApi
import com.yml.pagingdemo.domain.FetchNewsArticlesByQueryUseCase
import com.yml.pagingdemo.domain.NewsDataSource
import com.yml.pagingdemo.ui.model.NewsArticleUIModel
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(private val api: NewsApi) : ViewModel() {

    var query = ""

    val bitcoinNewsFlow = Pager(
        PagingConfig(

            // This is the page size to be used throughout pagination. Page boundaries
            // are also created with respect to this value
            pageSize = 10,

            // This will help the PagingDataAdapter trigger a page load when the
            // last bound ViewHolder is less than this many items from the page boundary
            prefetchDistance = 50
        )
    ) {
        // This closure is invoked every time `PagingDataAdapter.refresh()` is called
        // thus returning a fresh instance of a `PagingDataSource`
        NewsDataSource(query, FetchNewsArticlesByQueryUseCase.Factory(api))
    }.flow.map { pagingData ->
        pagingData.map { data ->
            NewsArticleUIModel.Data(data)
        }.insertSeparators(TerminalSeparatorType.FULLY_COMPLETE, this::separate)
    }.cachedIn(viewModelScope)

    private val calBefore = Calendar.getInstance()
    private val calAfter = Calendar.getInstance()

    private fun separate(
        before: NewsArticleUIModel.Data?,
        after: NewsArticleUIModel.Data?,
    ): NewsArticleUIModel.Header? {
        if (after == null) {
            return null
        }

        if (before == null) {
            return NewsArticleUIModel.Header(dateFormatter.format(after.news.publishedAt))
        }

        calBefore.time = before.news.publishedAt
        calAfter.time = after.news.publishedAt

        if (calBefore[Calendar.YEAR] != calAfter[Calendar.YEAR]
            || calBefore[Calendar.MONTH] != calAfter[Calendar.MONTH]
            || calBefore[Calendar.DAY_OF_MONTH] != calAfter[Calendar.DAY_OF_MONTH]
        ) {
            return NewsArticleUIModel.Header(dateFormatter.format(after.news.publishedAt))
        }

        return null
    }


    private val dateFormatter = SimpleDateFormat("MM/dd", Locale.getDefault())

    @Suppress("UNCHECKED_CAST")
    class Factory(private val api: NewsApi) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(api) as T
        }
    }

}