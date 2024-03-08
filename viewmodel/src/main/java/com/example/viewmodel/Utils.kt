package com.example.viewmodel

import com.example.viewmodel.recyclerview.RecyclerItemsData

object Utils {

    fun getItems(): MutableList<RecyclerItemsData> {
        val items: MutableList<RecyclerItemsData> = mutableListOf()

        for (i in 0..1000) {
            items.add(RecyclerItemsData(i, "Item-> $i"))
        }
        return items
    }
}