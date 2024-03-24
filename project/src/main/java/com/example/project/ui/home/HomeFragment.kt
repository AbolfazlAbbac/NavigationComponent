package com.example.project.ui.home

import android.annotation.SuppressLint
import android.content.pm.ModuleInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.project.databinding.FragmentHomeBinding
import com.example.project.ui.home.adapter.HomeGenresAdapter
import com.example.project.ui.home.adapter.HomeTopMoviesAdapter
import com.example.project.ui.home.adapter.MovieListAdapter
import com.example.project.utils.initRecycler
import com.example.project.utils.isShown
import com.example.project.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.processNextEventInCurrentThread
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: HomeTopMoviesAdapter

    @Inject
    lateinit var genresAdapter: HomeGenresAdapter

    @Inject
    lateinit var lastMovieAdapter: MovieListAdapter
    private val viewModel: HomeViewModel by viewModels()

    private val pagerSnapHelper: PagerSnapHelper by lazy { PagerSnapHelper() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
        }
        viewModel.topMovies(3)
        viewModel.allGenres()
        viewModel.getLastMovies()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //header Movies
            viewModel.topMoviesList.observe(viewLifecycleOwner) {
                topMoviesAdapter.differ.submitList(it.data)
                //RecyclerView
                topMoviesItemsRv.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    topMoviesAdapter
                )
                //Indicator
                pagerSnapHelper.attachToRecyclerView(topMoviesItemsRv)
                topMoviesIndicator.attachToRecyclerView(topMoviesItemsRv, pagerSnapHelper)
            }

            //Genres
            viewModel.allGenres.observe(viewLifecycleOwner) { response ->
                genresAdapter.differ.submitList(response)
                genresItemsRv.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    genresAdapter
                )
            }

            //movies list
            viewModel.lastMovies.observe(viewLifecycleOwner) {
                lastMovieAdapter.setData(it.data)

                lastMoviesItemsRv.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false),
                    lastMovieAdapter
                )
            }
            //loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    nestedScrollViewHome.isShown(false)
                    loadingHome.isShown(true)
                } else {
                    nestedScrollViewHome.isShown(true)
                    loadingHome.isShown(false)
                }
            }

            //click movies list(go to detail)
            lastMovieAdapter.onItemClickListener {
                goToDetail(it.id)
            }

            //click header list (go to detail)
            topMoviesAdapter.onItemListener {
                goToDetail(it.id)
            }
        }
    }

    private fun goToDetail(id: Int) {
        val direction = HomeFragmentDirections.toDetailFragment(id)
        findNavController().navigate(direction)
    }


}