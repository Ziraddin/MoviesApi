package com.zireddinismayilov.moviesapi

data class MoviesDTO(
    val id: Int,
    val original_title: String,
    val popularity: String,
    val overview: String,
    val release_date: String,
    var genre_ids: List<Int>

)
