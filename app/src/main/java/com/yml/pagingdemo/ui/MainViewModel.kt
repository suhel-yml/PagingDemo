package com.yml.pagingdemo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.yml.pagingdemo.data.NewsApi
import com.yml.pagingdemo.data.NewsDataSource

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
        NewsDataSource(api, query)
    }
        .flow
        .cachedIn(viewModelScope)

    @Suppress("UNCHECKED_CAST")
    class Factory(private val api: NewsApi) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(api) as T
        }
    }

}