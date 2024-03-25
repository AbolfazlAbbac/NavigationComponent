package com.example.project.ui.showImages

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.project.R
import com.example.project.databinding.FragmentShowImagesBinding
import com.example.project.utils.initRecycler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ShowImages : Fragment() {

    lateinit var binding: FragmentShowImagesBinding

    private val args: ShowImagesArgs by navArgs()
    private var imageList = emptyList<String>()

    @Inject
    lateinit var adapter: ShowImagesAdapter

    val snapHelper = PagerSnapHelper()
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
                //set Data for rv
                adapter.setData(imageList)

                showImagesRv.initRecycler(
                    LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false
                    ), adapter
                )

                snapHelper.attachToRecyclerView(showImagesRv)
                indicatorShowImages.attachToRecyclerView(showImagesRv, snapHelper)

            }
        }
    }

}