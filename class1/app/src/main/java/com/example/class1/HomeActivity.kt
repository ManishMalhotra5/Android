package com.example.class1

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class HomeActivity : AppCompatActivity() {
    lateinit var tvName : TextView
    lateinit var tvPass : TextView
    lateinit var tvEmail: TextView
    lateinit var llLayout : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvName = findViewById(R.id.tvName)
        tvEmail = findViewById(R.id.tvEmail)
        tvPass = findViewById(R.id.tvPass)
        llLayout = findViewById(R.id.llLayout)


        intent.let {
            var name = it.getStringExtra("name")
            var email = it.getStringExtra("email")
            var pass = it.getStringExtra("pass")

            if (pass != null) {
                if(pass.isNotEmpty()){
                    llLayout.visibility = View.VISIBLE
                    tvPass.text = pass.toString()

                }else{
                    llLayout.visibility = View.GONE

                }
            }

            tvName.text = name.toString()
            tvEmail.text = email.toString()

        }
    }
}