package com.example.dagger_hilt.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var text: String = ""
)
