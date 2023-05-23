package com.zireddinismayilov.moviesapi


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("search/movie")
    fun getSearchedMovieData(@Query("api_key") api_key: String, @Query("query") query: String): Call<Results?>?

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") api_key: String): Call<GenresResultsDTO>

    @GET("movie/popular")
    fun getPopularData(@Query("api_key") api_key: String): Call<List<MoviesDTO>>

    @GET("movie/upcoming")
    fun getUpcomingData(@Query("api_key") api_key: String): Call<List<MoviesDTO>>

}