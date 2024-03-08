package com.example.dagger_hilt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dagger_hilt.databinding.ItemMoviesBinding
import com.example.dagger_hilt.databinding.ItemNoteBinding
import com.example.dagger_hilt.db.NoteModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AdapterNotes @Inject constructor(@ApplicationContext context: Context) :
    RecyclerView.Adapter<AdapterNotes.ViewHolder>() {

    private lateinit var binding: ItemMoviesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNotes.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        binding = ItemMoviesBinding.inflate(view, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: AdapterNotes.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MoviesModel.Data) {
            binding.apply {
                ivMovie.load(item.poster)
                tvMovie.text = item.title
                root.setOnClickListener {
                    setOnItemClick?.let {
                        it(item)
                    }
                }
            }
        }

    }


    private var setOnItemClick: ((MoviesModel.Data) -> Unit)? = null

    fun setOnClickListenerAdapter(listener: (MoviesModel.Data) -> Unit) {
        setOnItemClick = listener
    }

    private

    var diffCallBack =

        object : DiffUtil.ItemCallback<MoviesModel.Data>() {
            override fun areItemsTheSame(
                oldItem: MoviesModel.Data,
                newItem: MoviesModel.Data
            ): Boolean {
                return oldItem.id == newItem.id

            }

            override fun areContentsTheSame(
                oldItem: MoviesModel.Data,
                newItem: MoviesModel.Data
            ): Boolean {
                return oldItem == newItem
            }
        }

    var differ = AsyncListDiffer(this, diffCallBack)
}