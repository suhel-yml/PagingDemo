package com.yml.pagingdemo.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yml.pagingdemo.R
import com.yml.pagingdemo.databinding.ItemErrorStateBinding

class ErrorStateAdapter : LoadStateAdapter<ErrorStateAdapter.ErrorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ErrorViewHolder {
        return ErrorViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ErrorViewHolder, loadState: LoadState) {
        (loadState as LoadState.Error).let { errorState ->
            holder.bind(errorState.error.localizedMessage)
        }
    }

    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Error
    }

    class ErrorViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_error_state, parent, false)
    ) {

        private val binding = ItemErrorStateBinding.bind(itemView)
        private val tvError = binding.tvError

        fun bind(error: String?) {
            tvError.text = error
        }

    }

}