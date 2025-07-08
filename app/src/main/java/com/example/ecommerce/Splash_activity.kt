package com.example.ecommerce

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecommerce.databinding.ActivitySplashBinding

class Splash_activity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startbtn.setOnClickListener {
            var intent=Intent(this@Splash_activity,Dashboard_activity::class.java)
            startActivity(intent)
        }

    }


}