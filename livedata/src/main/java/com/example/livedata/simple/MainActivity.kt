package com.example.livedata.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.livedata.CheckConnection
import com.example.livedata.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: ViewModel by viewModels()

    private val checkConnection: CheckConnection by lazy {
        CheckConnection(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            changeTextBtn.setOnClickListener {

                CoroutineScope(Dispatchers.Main).launch {
                    mainViewModel.changeText()
                }
            }
            mainViewModel.itemText.observe(this@MainActivity) {
                textView.text = it
            }

            checkConnection.observe(this@MainActivity) {
                if (it) {
                    Toast.makeText(this@MainActivity, "CONNECTED :)", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}