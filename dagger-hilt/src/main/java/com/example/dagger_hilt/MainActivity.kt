package com.example.dagger_hilt

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger_hilt.databinding.ActivityMainBinding
import com.example.dagger_hilt.di.RetrofitRepository
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var apiRetrofit: RetrofitRepository

    @Inject
    lateinit var adapterRv: AdapterNotes


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            progressBarMain.visibility = View.VISIBLE
            apiRetrofit.getAllMovies().enqueue(object : Callback<MoviesModel> {
                override fun onResponse(call: Call<MoviesModel>, response: Response<MoviesModel>) {
                    progressBarMain.visibility = View.GONE

                    if (response.isSuccessful) {
                        response.body()?.let { itBody ->
                            itBody.data?.let {
                                if (it.isNotEmpty()) {
                                    adapterRv.differ.submitList(it)
                                    rvMain.apply {
                                        layoutManager = LinearLayoutManager(this@MainActivity)
                                        adapter = adapterRv
                                    }
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<MoviesModel>, t: Throwable) {
                    Snackbar.make(
                        binding.root,
                        "There is an error here | ${t.message}",
                        Snackbar.LENGTH_SHORT
                    ).setAction("Ok", View.OnClickListener {
                    })
                        .setAction("Cancel", View.OnClickListener {

                        })

                        .show()
                }

            })
        }
    }
}