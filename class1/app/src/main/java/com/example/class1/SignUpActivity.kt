package com.example.class1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SignUpActivity : AppCompatActivity() {
     private fun emailChecker (email:String) : Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
        return email.matches(emailPattern.toRegex())
    }
    private lateinit var tvLogin : TextView
    private lateinit var btnSignUp : Button
    private lateinit var etName : EditText
    private lateinit var etEmail: EditText
    private lateinit var etPass : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvLogin = findViewById(R.id.tvLogin)
        btnSignUp = findViewById(R.id.btnSignUp)
        etName = findViewById(R.id.etName)
        etPass = findViewById(R.id.etPass)
        etEmail = findViewById(R.id.etEmail)


        btnSignUp.setOnClickListener {
            if(etName.text.toString().isEmpty()){
                etName.error = "Please enter your Name"
            }else if(etPass.text.toString().isEmpty()){
                etPass.error = "Please enter your Password"
                if(etPass.text.toString().length < 6){
                    etPass.error = "Password should be longer than 6 character"
                }
            }else if(etEmail.text.toString().isEmpty()){
                etEmail.error  = "Please enter your Email"
                if(!emailChecker(etEmail.text.toString())){
                    etEmail.error = "Please enter valid Email"
                }
            }else {
                val intent = Intent(this,HomeActivity::class.java)
                intent.putExtra("name",etName.text.toString())
                intent.putExtra("email",etEmail.text.toString())
                intent.putExtra("pass",etPass.text.toString())
                startActivity(intent)
                finish()
            }
        }

        tvLogin.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}