package com.example.myapplication.presentation.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var userAdapter:UserAdapter
    private lateinit var binding:ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        binding= ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun setupUI(){
        binding.apply {
            recyclerview.adapter=userAdapter

        }
    }
}