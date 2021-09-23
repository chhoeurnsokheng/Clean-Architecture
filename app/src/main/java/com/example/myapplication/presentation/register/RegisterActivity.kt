package com.example.myapplication.presentation.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.CarrierConfigManager
import com.example.myapplication.R
import javax.inject.Inject

class RegisterActivity : AppCompatActivity() {
    @Inject
    lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerViewModel.userManager
    }
}