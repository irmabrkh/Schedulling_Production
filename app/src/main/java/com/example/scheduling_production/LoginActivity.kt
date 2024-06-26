package com.example.scheduling_production

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scheduling_production.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity (){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}