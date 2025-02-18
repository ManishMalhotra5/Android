package com.example.mapapplication

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mapapplication.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    var askPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){
        if(it){
            Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.mapFragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync {
            googleMap ->
            addMarkers(googleMap)
        }
        getCurrLocation()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun getCurrLocation (){
        if(ActivityCompat.checkSelfPermission(
                this,android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                askPermission.launch(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        else {

        }

    }
    private fun addMarkers(googleMap: GoogleMap) {
        val location = LatLng(37.7749, -122.4194)
        googleMap.addMarker(
            MarkerOptions()
                .title("Place")
                .position(LatLng(0.0,8.0))
        )
    }
}