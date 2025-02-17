package com.example.class1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.class1.databinding.ActivityCounterBinding
import com.example.class1.databinding.FragmentCounterBinding
import com.example.class1.interfaces.CounterInterface

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding
    lateinit var counterInterface: CounterInterface
    var counter : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnIncrement.setOnClickListener{
            counter ++
            counterInterface.updateCounter(counter)

        }
        binding.btnDecrement.setOnClickListener{
            counter --
            counterInterface.updateCounter(counter)
        }


    }
}