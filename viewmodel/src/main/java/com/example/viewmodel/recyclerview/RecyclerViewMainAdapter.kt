package com.example.viewmodel.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.viewmodel.databinding.ItemRecyclerMainBinding

class RecyclerViewMainAdapter : Adapter<RecyclerViewMainAdapter.ViewHolderMain>() {

    private lateinit var binding: ItemRecyclerMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
        binding =
            ItemRecyclerMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolderMain()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    inner class ViewHolderMain : ViewHolder(binding.root) {

        fun bind(itemContent: RecyclerItemsData) {
            binding.apply {
                nameMainRecyclerTv.text = itemContent.name
                idMainRecyclerTv.text = itemContent.id.toString()
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<RecyclerItemsData>() {
        override fun areItemsTheSame(
            oldItem: RecyclerItemsData,
            newItem: RecyclerItemsData
        ): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: RecyclerItemsData,
            newItem: RecyclerItemsData
        ): Boolean {

            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

}