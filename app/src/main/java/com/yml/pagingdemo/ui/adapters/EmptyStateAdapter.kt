package com.yml.pagingdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yml.pagingdemo.R
import java.lang.ref.WeakReference

class EmptyStateAdapter(
    adapter: PagingDataAdapter<*, *>,
) : LoadStateAdapter<EmptyStateAdapter.EmptyStateViewHolder>() {

    private val adapterWeakRef = WeakReference(adapter)
    private val adapterItemCount: Int
        get() = adapterWeakRef.get()?.itemCount ?: -1

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): EmptyStateViewHolder {
        return EmptyStateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: EmptyStateViewHolder, loadState: LoadState) {

    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.NotLoading && adapterItemCount == 0
    }

    class EmptyStateViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_empty_state, parent, false)
    )

}