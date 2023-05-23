package com.zireddinismayilov.moviesapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zireddinismayilov.moviesapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        binding.genresRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        setContentView(binding.root)
        getSearcheddata()
        binding.goPopular.setOnClickListener {
            var intent = Intent(this, PopularsActivity::class.java)
            startActivity(intent)
        }
        binding.goUpcomings.setOnClickListener {
            var intent = Intent(this, UpcomingsActivity::class.java)
            startActivity(intent)
        }
    }

    fun getSearcheddata() {
        binding.searchBtn.setOnClickListener {
            val call: Call<Results?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getSearchedMovieData(Constants.apikey, binding.searchMovie.text.toString())
            call?.enqueue(object : Callback<Results?> {
                override fun onResponse(call: Call<Results?>, response: Response<Results?>) {
                    var list: Results = response?.body() as Results
                    binding.title.setText("Title:  " + list.results.get(1).original_title)
                    binding.popularity.setText("Popularity:  " + list.results.get(1).popularity)
                    binding.releaseDate.setText("Release Date:  " + list.results.get(1).release_date)


                    val call2: Call<GenresResultsDTO>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getGenres(Constants.apikey)
                    call2?.enqueue(object : Callback<GenresResultsDTO> {
                        override fun onResponse(call: Call<GenresResultsDTO>, response: Response<GenresResultsDTO>) {
                            var list2: GenresResultsDTO = response?.body() as GenresResultsDTO
                            var genres: MutableList<GenresDTO>? = mutableListOf()
                            for (i in list2.genres) {
                                if (i.id in list.results.get(1).genre_ids) {
                                    genres?.add(i)
                                }
                            }
                            binding.genresRecyclerView.adapter = Adapter(genres)
                        }

                        override fun onFailure(call: Call<GenresResultsDTO>, t: Throwable) {
                            Toast.makeText(applicationContext, "Error has occured on genres!", Toast.LENGTH_SHORT).show()
                        }
                    })

                }

                override fun onFailure(call: Call<Results?>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error has occured on search!", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}