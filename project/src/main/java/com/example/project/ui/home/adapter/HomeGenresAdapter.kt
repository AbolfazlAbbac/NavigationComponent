package com.example.project.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.project.databinding.ItemGenresBinding
import com.example.project.models.ResponseGenres
import com.example.project.ui.home.adapter.HomeGenresAdapter.*
import javax.inject.Inject

class HomeGenresAdapter @Inject constructor() : RecyclerView.Adapter<ViewHolderG>() {

    lateinit var binding: ItemGenresBinding

    inner class ViewHolderG : ViewHolder(binding.root) {

        fun bind(item: ResponseGenres.ResponseGenresItem) {
            binding.apply {
                textGenre.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderG {
        binding = ItemGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderG()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolderG, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)

    }


    private val diffUtil = object : DiffUtil.ItemCallback<ResponseGenres.ResponseGenresItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseGenres.ResponseGenresItem, newItem: ResponseGenres.ResponseGenresItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseGenres.ResponseGenresItem, newItem: ResponseGenres.ResponseGenresItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)
}