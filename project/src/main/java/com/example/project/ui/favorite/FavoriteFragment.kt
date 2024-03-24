package com.example.project.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project.R
import com.example.project.databinding.FragmentFavoriteBinding
import com.example.project.utils.initRecycler
import com.example.project.utils.isShown
import com.example.project.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.intellij.lang.annotations.JdkConstants.InputEventMask
import javax.inject.Inject


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var adapter: FabAdapter

    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel.getAllFabMovie()

            //Get Data
            viewModel.movieFabList.observe(viewLifecycleOwner) { list ->
                adapter.setData(list)
                fabRv.initRecycler(LinearLayoutManager(requireContext()), adapter)
            }

            //Empty view
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    fabRv.isShown(false)
                    lottieEmptyFab.isShown(true)
                } else {
                    fabRv.isShown(true)
                    lottieEmptyFab.isShown(false)
                }
            }

            //Click Item go to detail
            adapter.onItemClickListener {
                goToDetail(it.id)
            }
        }
    }

    private fun goToDetail(id: Int) {
        val direction = FavoriteFragmentDirections.toDetailFragment(id)
        findNavController().navigate(direction)
    }
}