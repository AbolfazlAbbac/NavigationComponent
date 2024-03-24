package com.example.project.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.project.R
import com.example.project.databinding.FragmentDetailBinding
import com.example.project.db.FabEntity
import com.example.project.utils.initRecycler
import com.example.project.utils.isShown
import com.example.project.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    @Inject
    lateinit var fabEntity: FabEntity

    @Inject
    lateinit var adapter: DetailImagesAdapter
    val args: DetailFragmentArgs by navArgs()
    var itemId = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemId = args.itemId
        if (itemId > 0) {
            viewModel.getDetail(itemId)
        } else {
            Toast.makeText(requireContext(), "Couldn't Refresh", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    nestedScrollViewDetail.isShown(false)
                    loadingDetail.isShown(true)
                } else {
                    nestedScrollViewDetail.isShown(true)
                    loadingDetail.isShown(false)
                }

            }
            //Get Data
            viewModel.detail.observe(viewLifecycleOwner) { response ->

                adapter.differ.submitList(response.images)
                posterIVBig.load(response.poster)
                posterIVNormal.load(response.poster) {
                    crossfade(true)
                    crossfade(800)
                }
                nameTvDetail.text = response.title
                rateTvDetail.text = response.imdbRating
                yearTvDetail.text = " ${response.year} | ${response.runtime}"
                countryTvDetail.text = response.country
                mainSummeryTvDetail.text = response.plot
                nameActorsTvDetail.text = response.actors
                actorsPhotoDetail.initRecycler(
                    LinearLayoutManager(
                        requireContext(), LinearLayoutManager.HORIZONTAL, false
                    ), adapter
                )
                favBtnDetail.setOnClickListener {
                    if (itemId > 0) {

                        fabEntity.id = itemId
                        fabEntity.year = response.year
                        fabEntity.name = response.title
                        fabEntity.conutry = response.country
                        fabEntity.rate = response.imdbRating.toString()
                        fabEntity.poster = response.poster

                        viewModel.favoriteMovie(itemId, fabEntity)
                    }
                }
            }

            lifecycleScope.launch {
                if (viewModel.existMovie(itemId)) {
                    favBtnDetail.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(), R.drawable.bookmark_added
                        )
                    )
                } else {
                    favBtnDetail.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(), R.drawable.bookmark_icon
                        )
                    )
                }
            }

            //check favorite
            viewModel.isFavorite.observe(viewLifecycleOwner) {
                if (it) {
                    favBtnDetail.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(), R.drawable.bookmark_added
                        )
                    )
                } else {
                    favBtnDetail.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(), R.drawable.bookmark_icon
                        )
                    )
                }
            }

            //Back
            backBtnDetail.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}