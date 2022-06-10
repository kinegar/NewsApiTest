package iglo.indocyber.newsapitest.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import iglo.indocyber.common.entity.source.Source
import iglo.indocyber.newsapitest.databinding.HomeSourceItemLayoutBinding

class SourceAdapter(val selectSource:(Source)->Unit): RecyclerView.Adapter<SourceViewHolder>() {
    private val differData = AsyncListDiffer<Source>(this,differ)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder =
        SourceViewHolder(HomeSourceItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false))


    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val data=differData.currentList[position]
        holder.binding.data = data
        holder.binding.cardView.setOnClickListener {
            selectSource(data)
        }
    }

    fun submitData(data: List<Source>){
        differData.submitList(data)
    }

    override fun getItemCount(): Int = differData.currentList.size

    companion object{
        val differ = object : DiffUtil.ItemCallback<Source>(){
            override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class SourceViewHolder(val binding: HomeSourceItemLayoutBinding):RecyclerView.ViewHolder(binding.root)