package com.example.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.coroutineScope
import com.example.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val dataStore: DataStore<Preferences> by preferencesDataStore("userInfo")

    private val userName = stringPreferencesKey("USERNAME")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            btnSave.setOnClickListener {
                CoroutineScope(IO).launch {
                    saveDataStore(editTextEt.text.toString())
                }
            }

            lifecycle.coroutineScope.launch {
                getDataStore().collect() {
                    showTextTv.text = it
                }
            }
        }
    }


    private suspend fun saveDataStore(name: String) {
        dataStore.edit {
            it[userName] = name
        }
    }

    private fun getDataStore() = dataStore.data.map {
        it[userName] ?: ""
    }
}