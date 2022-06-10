package iglo.indocyber.newsapitest.fragment.everything

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import iglo.indocyber.common.ext.hide
import iglo.indocyber.common.ext.visible
import iglo.indocyber.newsapitest.databinding.EverythingItemStateLayoutBinding

class EverythingPagingStateAdapter(val retry:()->Unit) : LoadStateAdapter<EverythingPagingStateViewHolder>() {
    override fun onBindViewHolder(holder: EverythingPagingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): EverythingPagingStateViewHolder =
        EverythingPagingStateViewHolder(
            EverythingItemStateLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        ,retry).apply {
            this.bind(loadState)
        }

}

class EverythingPagingStateViewHolder(val binding: EverythingItemStateLayoutBinding,val retry:()->Unit) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(loadState: LoadState) {
        binding.errorContainer.setOnClickListener {
            retry()
        }
        when (loadState) {
            is LoadState.Error -> {
                binding.errorContainer.visible()
                binding.loadingContainer.hide()
            }
            is LoadState.Loading -> {
                binding.errorContainer.hide()
                binding.loadingContainer.visible()
            }
            is LoadState.NotLoading -> {
                binding.errorContainer.hide()
                binding.loadingContainer.hide()
            }
        }
    }
}