package com.example.project.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.databinding.FragmentSearchBinding
import com.example.project.ui.home.adapter.MovieListAdapter
import com.example.project.utils.initRecycler
import com.example.project.utils.isShown
import com.example.project.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var moviesAdapter: MovieListAdapter

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            searchEt.addTextChangedListener {
                val name = it.toString()
                if (name.isNotEmpty()) {
                    viewModel.searchMovies(name)
                }
            }
            viewModel.searchedMovies.observe(viewLifecycleOwner) { response ->
                moviesAdapter.setData(response.data)
                searchRv.initRecycler(
                    LinearLayoutManager(
                        requireContext()), moviesAdapter
                )
            }
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    loadingSearch.isShown(true)
                } else {
                    loadingSearch.isShown(false)
                }
            }
            viewModel.emptyList.observe(viewLifecycleOwner) {
                if (it) {
                    lottieEmptySearch.isShown(true)
                    searchRv.isShown(false)
                } else {
                    lottieEmptySearch.isShown(false)
                    searchRv.isShown(true)
                }
            }

        }
    }
}