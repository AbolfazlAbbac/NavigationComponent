package com.example.project.ui.detail

import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.project.databinding.ImagesDetailItemBinding
import com.example.project.models.ResponseDetail
import javax.inject.Inject

class DetailImagesAdapter @Inject constructor() :
    RecyclerView.Adapter<DetailImagesAdapter.ViewHolderImages>() {

    lateinit var binding: ImagesDetailItemBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderImages {
        binding =
            ImagesDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolderImages()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }

    override fun onBindViewHolder(holder: ViewHolderImages, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    inner class ViewHolderImages : ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.apply {
                imageActorsDetail.load(item) {
                    crossfade(true)
                    crossfade(800)
                }
                binding.root.setOnClickListener {
                    imageClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var imageClickListener: ((String) -> Unit)? = null

    fun imageItemListener(listener: (String) -> Unit) {
        imageClickListener = listener
    }

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)
}