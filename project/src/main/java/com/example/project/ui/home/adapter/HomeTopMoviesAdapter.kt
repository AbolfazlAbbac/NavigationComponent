package com.example.project.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import coil.load
import com.example.project.databinding.ItemTopMoivesRvBinding
import com.example.project.models.ResponseMovieList.*
import javax.inject.Inject

class HomeTopMoviesAdapter @Inject constructor() : Adapter<HomeTopMoviesAdapter.ViewHolder>() {
    lateinit var binding: ItemTopMoivesRvBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemTopMoivesRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = 5


    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Data) {
            binding.apply {
                coverImg.load(data.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                moviesNameTv.text = data.title
                infoMoviesTv.text = "${data.imdbRating} | ${data.country} | ${data.year}"

                binding.root.setOnClickListener {
                    onItemListener?.let {
                        it(data)
                    }
                }
            }
        }
    }

    private var onItemListener: ((Data) -> Unit)? = null

    fun onItemListener(listener: (Data) -> Unit) {
        onItemListener = listener
    }


    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(
            oldItem: Data, newItem: Data
        ): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(
            oldItem: Data, newItem: Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}