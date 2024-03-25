package com.example.project.ui.showImages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.project.R
import com.example.project.databinding.FragmentShowImagesBinding

class ShowImages : Fragment() {

    lateinit var binding: FragmentShowImagesBinding

    private val args: ShowImagesArgs by navArgs()
    private var imageList = emptyList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageList = args.imagesUrl.toList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShowImagesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            if (imageList.isNotEmpty()) {
                Log.e("Image List", "onViewCreated: $imageList")

            }
        }
    }

}