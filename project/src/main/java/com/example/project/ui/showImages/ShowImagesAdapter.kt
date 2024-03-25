package com.example.project.ui.showImages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import coil.load
import com.example.project.databinding.ItemShowImagesBinding
import javax.inject.Inject

class ShowImagesAdapter @Inject constructor() : Adapter<ShowImagesAdapter.ViewHolder>() {

    lateinit var binding: ItemShowImagesBinding
    private var emptyList = emptyList<String>()

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun holder(item: String) {
            binding.apply {
                imagesIv.load(item) {
                    crossfade(true)
                    crossfade(800)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemShowImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun getItemCount(): Int {
        return emptyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.holder(emptyList[position])
        holder.setIsRecyclable(false)
    }

    fun setData(data: List<String>) {
        val diffUtil = CustomDiffUtils(emptyList, data)
        val calculate = DiffUtil.calculateDiff(diffUtil)
        emptyList = data
        calculate.dispatchUpdatesTo(this)
    }

    class CustomDiffUtils(private val oldItems: List<String>, private val newItems: List<String>) :
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