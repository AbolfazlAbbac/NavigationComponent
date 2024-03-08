package com.example.livedata.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.livedata.R
import com.example.livedata.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {

    private lateinit var binding: FragmentNoteListBinding

    private val adapterFragment: Adapter by lazy { Adapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            fabGoToAddPage.setOnClickListener {
                findNavController().navigate(R.id.listtoadd)
            }

            (activity as NoteActivity).noteDb.databaseDao().getAllNotes()
                .observe(viewLifecycleOwner) {
                    adapterFragment.differ.submitList(it)
                }

            listNotesRv.apply {
                layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = adapterFragment
            }


        }
    }

}