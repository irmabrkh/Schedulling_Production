package com.example.scheduling_production

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.example.scheduling_production.databinding.ActivityRegisterBinding
import com.example.scheduling_production.model.users

class RegisterActivity : AppCompatActivity (){
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val btnRegister = binding.btnRegsitered
        btnRegister.setOnClickListener {
            val username = binding.fieldUsernameRegister.text.toString()
            val name = binding.fieldNameRegister.text.toString()
            val email = binding.fieldEmailRegister.text.toString()
            val password = binding.fieldPassRegister.text.toString()
            val confirmPass = binding.fieldCpassRegister.text.toString()

            if (username.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {

                if (password == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task ->

                        if (task.isSuccessful) {
                            val currentUser = firebaseAuth.currentUser
                            val userId = currentUser?.uid

                            val userRef = database.getReference("users")
                            val user = users(username, name, email, password)

                            if (userId != null) {
                                userRef.child(userId).setValue(user)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show()

                                        val intent = Intent(this, LoginActivity::class.java)
                                        startActivity(intent)
                                    }
                                    .addOnFailureListener {
                                            exception ->
                                        Toast.makeText(this, "Gagal menyimpan data pengguna: ${exception.message}", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        } else {
                            Toast.makeText(this, "Pendaftaran Gagal: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Kata sandi tidak cocok!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }

        val btnLogin: TextView = binding.registered
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}