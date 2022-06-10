package iglo.indocyber.newsapitest.fragment.everything

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import iglo.indocyber.common.entity.everything.Article
import iglo.indocyber.newsapitest.databinding.EverythingItemLayoutBinding

class EverythingPagingAdapter(val selectArticle:(Article)->Unit) : PagingDataAdapter<Article, EverythingPagingViewHolder>(differ) {
    companion object {
        val differ = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: EverythingPagingViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.article = data
        holder.binding.cardView.setOnClickListener {
            data?.let { article->
                selectArticle(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EverythingPagingViewHolder =
        EverythingPagingViewHolder(
            EverythingItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
}

class EverythingPagingViewHolder(val binding: EverythingItemLayoutBinding) :
    RecyclerView.ViewHolder(binding.root)