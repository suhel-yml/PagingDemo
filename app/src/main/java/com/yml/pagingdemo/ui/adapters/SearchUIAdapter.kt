package com.yml.pagingdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.yml.pagingdemo.R
import com.yml.pagingdemo.databinding.ItemSearchUiBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class SearchUIAdapter(
    initialQuery: String = ""
) : RecyclerView.Adapter<SearchUIAdapter.SearchUIViewHolder>() {

    private val queryFlowInternal = MutableStateFlow(initialQuery)
    val queryFlow: Flow<String> get() = queryFlowInternal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUIViewHolder {
        return SearchUIViewHolder(parent)
    }

    override fun onBindViewHolder(holder: SearchUIViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }

    inner class SearchUIViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_ui, parent, false)
    ) {

        private val binding = ItemSearchUiBinding.bind(itemView)
        private val etSearchQuery = binding.etSearchQuery

        init {
            etSearchQuery.addTextChangedListener { text ->
                queryFlowInternal.value = text.toString()
            }
        }

    }

}