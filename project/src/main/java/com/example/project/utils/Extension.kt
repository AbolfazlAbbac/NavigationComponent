package com.example.project.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager

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