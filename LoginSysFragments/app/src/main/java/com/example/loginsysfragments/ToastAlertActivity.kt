package com.example.loginsysfragments

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.loginsysfragments.databinding.ActivityToastAlertBinding
import com.example.loginsysfragments.databinding.FragmentToastAlertBinding

class ToastAlertActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var binding: ActivityToastAlertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityToastAlertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        navController = findNavController(R.id.fragment1)

        binding.btmNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    navController.navigate(R.id.spinnerFragment)
                }
                R.id.list -> {
                    navController.navigate(R.id.listViewFragment2)
                }
                R.id.profile -> {
                    navController.navigate(R.id.recyclerListViewFragment)
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}