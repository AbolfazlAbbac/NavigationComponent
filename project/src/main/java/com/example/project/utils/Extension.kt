package com.example.project.utils

import android.graphics.Path.Direction
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.project.ui.home.HomeFragment
import com.example.project.ui.home.HomeFragmentDirections

fun View.isShown(boolean: Boolean) {
    if (boolean) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }

}

fun RecyclerView.initRecycler(
    layoutManager: LayoutManager,
    adapter: Adapter<*>
) {

    this.layoutManager = layoutManager
    this.adapter = adapter
    this.setHasFixedSize(true)
}