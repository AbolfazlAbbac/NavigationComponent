package com.example.project.ui.favorite

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.project.databinding.ItemLastMoviesBinding
import com.example.project.db.FabEntity
import javax.inject.Inject

class FabAdapter @Inject constructor() : RecyclerView.Adapter<FabAdapter.FabViewHolder>() {

    lateinit var binding: ItemLastMoviesBinding
    private var emptyList = emptyList<FabEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FabViewHolder {
        binding = ItemLastMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FabViewHolder()
    }

    override fun onBindViewHolder(holder: FabViewHolder, position: Int) {
        holder.bind(emptyList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return emptyList.size
    }


    inner class FabViewHolder : ViewHolder(binding.root) {
        fun bind(item: FabEntity) {
            binding.apply {
                nameMoviesLastItemsTv.text = item.name
                yearMovie.text = item.year
                rateMovie.text = item.rate
                countryMovie.text = item.conutry
                coverImgLastItemsIv.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                binding.root.setOnClickListener {
                    onItemListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemListener: ((FabEntity) -> Unit)? = null

    fun onItemClickListener(listener: (FabEntity) -> Unit) {
        onItemListener = listener
    }

    fun setData(data: List<FabEntity>) {
        val diff = DiffUtils(emptyList, data)
        val calculate = DiffUtil.calculateDiff(diff)
        emptyList = data
        calculate.dispatchUpdatesTo(this)

    }

    class DiffUtils(private val oldItems: List<FabEntity>, private val newItems: List<FabEntity>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItems.size
        }

        override fun getNewListSize(): Int {
            return newItems.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] === newItems[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] === newItems[newItemPosition]
        }

    }
}