package com.example.livedata.notes

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.livedata.databinding.FragmentAddNoteBinding
import com.example.livedata.notes.db.NotesData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class AddNoteFragment : Fragment() {
    private lateinit var binding: FragmentAddNoteBinding
    private lateinit var notesData: NotesData


    companion object {
        private val Context.dataStore by preferencesDataStore("user_preferences")

        private val nameDataStoreKey = stringPreferencesKey("name")
        private val descDataStoreKey = stringPreferencesKey("desc")

    }

    private lateinit var information: Information
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {


            fabAddNewNote.setOnClickListener {
                val title = titleEt.text.toString()
                var desc = descEt.text.toString()
                CoroutineScope(IO).launch {
                    information = Information(titleEt.text.toString(), descEt.text.toString())
                    saveDataStore(information)
                }
                if (title.isNotEmpty() && desc.isEmpty()) {
                    desc = "Empty"
                    notesData = NotesData(0, title, desc)
                    (activity as NoteActivity).noteDb.databaseDao().addNewNote(notesData)
                    findNavController().popBackStack()

                } else if (title.isNotEmpty() && desc.isNotEmpty()) {
                    notesData = NotesData(0, title, desc)
                    (activity as NoteActivity).noteDb.databaseDao().addNewNote(notesData)
                    findNavController().popBackStack()

                } else {
                    Toast.makeText(context, "Title Cannot be empty!", Toast.LENGTH_SHORT).show()
                }


            }
            lifecycle.coroutineScope.launch {
                getName()?.collect {
                    titleEt.setText(it.name)
                    descEt.setText(it.desc)
                }
            }
        }
    }

    private suspend fun saveDataStore(information: Information) {
        context?.dataStore?.edit {
            it[nameDataStoreKey] = information.name
            it[descDataStoreKey] = information.desc
        }

    }

    private fun getName() = context?.dataStore?.data?.map {
        val name = it[nameDataStoreKey] ?: "Enter Your Nameeeeeeeee"
        val desc = it[descDataStoreKey] ?: "Enter Your desccccccccc"
        Information(name, desc)
    }
}

data class Information(var name: String, var desc: String)

