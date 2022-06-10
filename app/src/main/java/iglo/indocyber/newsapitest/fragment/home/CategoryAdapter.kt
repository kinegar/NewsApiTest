package iglo.indocyber.newsapitest.fragment.home

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import iglo.indocyber.newsapitest.databinding.HomeItemLayoutBinding

class CategoryAdapter(
    private val selectCategory: (String, Int) -> Unit,
    private val selectedCategory: () -> String?
) : RecyclerView.Adapter<CategoryViewHolder>() {
    private val dataDiffer = AsyncListDiffer<String>(this, differ)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            HomeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            selectCategory, selectedCategory
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data = dataDiffer.currentList[position]
        holder.binding.data = data
        holder.bind(data, position)

    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    fun submitData(data: List<String>) {
        dataDiffer.submitList(data)
    }

    companion object {
        val differ = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return true
            }

        }
    }
}

class CategoryViewHolder(
    val binding: HomeItemLayoutBinding,
    val selectCategory: (String, Int) -> Unit,
    val selectedCategory: () -> String?
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String, position: Int) {
        if (data == selectedCategory()) {
            binding.category.setBackgroundColor(Color.GREEN)
        } else {
            binding.category.setBackgroundColor(Color.BLUE)
        }
        binding.category.setOnClickListener {
            selectCategory(data, position)
        }
    }
}