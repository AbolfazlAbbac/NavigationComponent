package com.example.project.models


import com.example.project.models.ResponseGenres.*
import com.google.gson.annotations.SerializedName

class ResponseGenres : ArrayList<ResponseGenresItem>(){
    data class ResponseGenresItem(
        @SerializedName("id")
        val id: Int = 0, // 1
        @SerializedName("name")
        val name: String = "" // Crime
    )
}