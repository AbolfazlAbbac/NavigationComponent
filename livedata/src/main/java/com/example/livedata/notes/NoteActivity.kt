package com.example.livedata.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.livedata.R
import com.example.livedata.databinding.ActivityNoteBinding
import com.example.livedata.notes.db.RoomDatabase

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private val dataStore: DataStore<Preferences> by preferencesDataStore("settings")

    val noteDb by lazy {
        Room.databaseBuilder(this, RoomDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().allowMainThreadQueries().build()

    }

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            navController = findNavController(R.id.nav_host)
            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController, appBarConfiguration)
        }
    }


    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}