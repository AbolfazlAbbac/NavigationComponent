package com.example.dagger_hilt.repository

import com.example.dagger_hilt.db.Dao
import com.example.dagger_hilt.db.NoteModel
import javax.inject.Inject

class DbRepository @Inject constructor(private val dao: Dao) {
    fun saveNewNoteFromRepository(noteModel: NoteModel) = dao.saveNewNote(noteModel)
    fun getAllNotesFromRepository() = dao.getAllNotes()
    fun deleteAllNotes() = dao.deleteNotes()
}