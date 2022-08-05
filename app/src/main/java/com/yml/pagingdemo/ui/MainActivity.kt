package com.yml.pagingdemo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.yml.pagingdemo.data.NewsApi
import com.yml.pagingdemo.databinding.ActivityMainBinding
import com.yml.pagingdemo.ui.adapters.*
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory(NewsApi.create())
    }

    private lateinit var binding: ActivityMainBinding

    @OptIn(FlowPreview::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvRepos.layoutManager = layoutManager

        val searchUIAdapter = SearchUIAdapter(viewModel.query)
        val newsArticlesAdapter = NewsArticlesAdapter()

        val loadingStateAdapter = LoadingStateAdapter()
        val emptyStateAdapter = EmptyStateAdapter(newsArticlesAdapter)
        val errorStateAdapter = ErrorStateAdapter()

        // On change of the query, update the query in the ViewModel to be used
        // by the new PagingDataSource instance which will be created on calling
        // `refresh()` on the `PagingDataAdapter`.
        // FYI: The old PagingDataSource will be invalidated and removed automatically
        lifecycleScope.launch {
            searchUIAdapter.queryFlow
                .debounce(500)
                .drop(1)
                .collectLatest { query ->
                    viewModel.query = query
                    newsArticlesAdapter.refresh()
                }
        }

        // Update the `LoadingStateAdapter` and the `ErrorStateAdapter` with the
        // `Paging` `LoadState` so that they can display the correct indicators
        lifecycleScope.launch {
            newsArticlesAdapter.loadStateFlow.collectLatest { loadStates ->
                // `loadStates.source` directly represents the state of the
                // PagingDataSource and `.refresh` represents the complete reload
                // state of the data source
                val refreshLoadState = loadStates.source.refresh

                loadingStateAdapter.loadState = refreshLoadState
                errorStateAdapter.loadState = refreshLoadState
                emptyStateAdapter.loadState = refreshLoadState

                binding.srlRepos.isRefreshing = refreshLoadState is LoadState.Loading
            }
        }

        // Concatenate different parts of the UI represented with different adapters
        // in order of their appearance in the UI. Different ViewPools are maintained
        // for each adapter
        binding.rvRepos.adapter = ConcatAdapter(
            searchUIAdapter,
            loadingStateAdapter,
            emptyStateAdapter,
            errorStateAdapter,
            newsArticlesAdapter
        )

        binding.rvRepos.addItemDecoration(HorizontalDividerDecoration())

        // Refresh the PagingDataAdapter responsible for paginating through
        // the news articles
        binding.srlRepos.setOnRefreshListener {
            newsArticlesAdapter.refresh()
        }

        // Update the adapter to merge the add/change/removal of pages
        lifecycleScope.launch {
            viewModel.bitcoinNewsFlow.collectLatest { pagingData ->
                newsArticlesAdapter.submitData(pagingData)
            }
        }
    }
}