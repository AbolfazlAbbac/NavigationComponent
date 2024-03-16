package com.example.project.models


import com.google.gson.annotations.SerializedName

data class ResponseMovieList(
    @SerializedName("data")
    val `data`: List<Data> = listOf(),
    @SerializedName("metadata")
    val metadata: Metadata = Metadata()
) {
    data class Data(
        @SerializedName("country")
        val country: String = "", // USA, UK
        @SerializedName("genres")
        val genres: List<String> = listOf(),
        @SerializedName("id")
        val id: Int = 0, // 4
        @SerializedName("images")
        val images: List<String>? = listOf(),
        @SerializedName("imdb_rating")
        val imdbRating: String = "", // 9.0
        @SerializedName("poster")
        val poster: String = "", // https://moviesapi.ir/images/tt0468569_poster.jpg
        @SerializedName("title")
        val title: String = "", // The Dark Knight
        @SerializedName("year")
        val year: String = "" // 2008
    )

    data class Metadata(
        @SerializedName("current_page")
        val currentPage: String = "", // 1
        @SerializedName("page_count")
        val pageCount: Int = 0, // 4
        @SerializedName("per_page")
        val perPage: Int = 0, // 10
        @SerializedName("total_count")
        val totalCount: Int = 0 // 32
    )
}