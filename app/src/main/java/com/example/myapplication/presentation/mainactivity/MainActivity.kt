package com.example.myapplication.presentation.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.BuildConfig
import com.example.myapplication.R
import com.example.myapplication.data.api.RetrofitService
import com.example.myapplication.data.repository.MainRepository
import com.example.myapplication.databinding.ActivityMainBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private val TAG ="ManiActivity"
    private lateinit var binding: ActivityMainBinding
    private val retrofitService=RetrofitService.getInstance()
    val adapter =MainAdapter()
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this,MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        setUpView()
    }

    fun setUpView(){
        binding.apply {
            recyclerview.adapter=adapter
            viewModel.movieList.observe(this@MainActivity, Observer {
                adapter.setMovies(it)
            })
        }
        viewModel.errorMessage.observe(this, Observer {  })
        viewModel.getAllMovies()
    }
}