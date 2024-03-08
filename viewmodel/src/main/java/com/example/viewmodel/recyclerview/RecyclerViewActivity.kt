package com.example.viewmodel.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.viewmodel.databinding.ActivityRecycelerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecycelerViewBinding

    private val viewModel: ViewModelRv by viewModels()
    private val recyclerViewMainAdapter: RecyclerViewMainAdapter by lazy { RecyclerViewMainAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycelerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            recyclerViewMainAdapter.differ.submitList(viewModel.items)
            rvMain.apply {
                layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
                adapter = recyclerViewMainAdapter
            }

        }
    }
}