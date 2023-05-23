package com.zireddinismayilov.moviesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zireddinismayilov.moviesapi.databinding.ActivityPopularsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPopularsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPopularsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.popularsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getdata()
    }

    fun getdata() {
        val call: Call<List<MoviesDTO>>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getPopularData(Constants.apikey)
        call?.enqueue(object : Callback<List<MoviesDTO>?> {
            override fun onResponse(call: Call<List<MoviesDTO>?>, response: Response<List<MoviesDTO>?>) {
                var list: List<MoviesDTO> = response.body() as List<MoviesDTO>
                binding.popularsRecyclerView.adapter = Adapter2(list)
            }

            override fun onFailure(call: Call<List<MoviesDTO>?>, t: Throwable) {
                Toast.makeText(applicationContext, "Error has occured!", Toast.LENGTH_SHORT).show()
            }

        })
    }
}