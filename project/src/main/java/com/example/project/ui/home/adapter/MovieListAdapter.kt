package com.example.project.ui.home.adapter

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.*
import androidx.room.util.recursiveFetchArrayMap
import coil.load
import com.example.project.databinding.ItemLastMoviesBinding
import com.example.project.models.ResponseMovieList
import com.example.project.ui.home.adapter.MovieListAdapter.*
import okhttp3.internal.notifyAll
import javax.inject.Inject

class MovieListAdapter @Inject constructor() : Adapter<ViewHolderMovieList>() {

    lateinit var binding: ItemLastMoviesBinding
    private var movieList = emptyList<ResponseMovieList.Data>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovieList {
        binding = ItemLastMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderMovieList()
    }

    override fun onBindViewHolder(holder: ViewHolderMovieList, position: Int) {
        holder.bind(movieList[position])
        holder.setIsRecyclable(false)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class ViewHolderMovieList : ViewHolder(binding.root) {

        fun bind(item: ResponseMovieList.Data) {
            binding.apply {
                nameMoviesLastItemsTv.text = item.title
                yearMovie.text = item.year
                rateMovie.text = item.imdbRating
                countryMovie.text = item.country
                coverImgLastItemsIv.load(item.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                binding.root.setOnClickListener {
                    onItemClick?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClick: ((ResponseMovieList.Data) -> Unit)? = null

    fun onItemClickListener(listener: (ResponseMovieList.Data) -> Unit) {
        onItemClick = listener
    }

    fun setData(data: List<ResponseMovieList.Data>) {
        val movieDiffUtil = DiffUtils(movieList, data)
        val diffCalculate = DiffUtil.calculateDiff(movieDiffUtil)
        movieList = data
        diffCalculate.dispatchUpdatesTo(this)
    }

    class DiffUtils(
        private val oldItems: List<ResponseMovieList.Data>,
        private val newItems: List<ResponseMovieList.Data>
    ) : DiffUtil.Callback() {
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