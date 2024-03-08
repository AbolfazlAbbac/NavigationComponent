package com.example.livedata.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.livedata.databinding.ItemRecyclerMainBinding
import com.example.livedata.notes.db.NotesData

class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private lateinit var binding: ItemRecyclerMainBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NotesData) {
            binding.titleTv.text = item.title
            binding.descTv.text = item.desc
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemRecyclerMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder()
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }


    val diffCallBack = object : DiffUtil.ItemCallback<NotesData>() {
        override fun areItemsTheSame(oldItem: NotesData, newItem: NotesData): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: NotesData, newItem: NotesData): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)
}