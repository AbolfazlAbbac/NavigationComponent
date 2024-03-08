package com.example.viewmodel.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            counterTv.text = mainViewModel.counter.toString()
            countPlusBtn.setOnClickListener {
                mainViewModel.counterPlus()
                counterTv.text = mainViewModel.counter.toString()
            }
            countMinusBtn.setOnClickListener {
                mainViewModel.counterMinus()
                counterTv.text = mainViewModel.counter.toString()
            }
        }
    }
}