package com.example.class1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
//    lateinit var tvSignUp : TextView
//    lateinit var btnLogin : Button
//    lateinit var  etName : EditText
//    lateinit var  etEmail : EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        tvSignUp = findViewById(R.id.tvSignUp)
//        etName  = findViewById(R.id.etName)
//        etEmail = findViewById(R.id.etEmail)
//        btnLogin = findViewById(R.id.btnLogin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        tvSignUp.setOnClickListener {
//            startActivity(Intent(this,SignUpActivity::class.java))
//            finish()
//        }
//        btnLogin.setOnClickListener {
//            if (etName.text.toString().isEmpty()) {
//                etName.error = "Please enter your name"
//            } else if (etEmail.text.toString().isEmpty()) {
//                etEmail.error = "Please enter your email"
//            } else {
//                var intent = Intent(this, HomeActivity::class.java)
//                intent.putExtra("name",etName.text.toString())
//                intent.putExtra("email",etEmail.text.toString())
//                startActivity(intent)
//                finish()
//            }
//        }
    }
}