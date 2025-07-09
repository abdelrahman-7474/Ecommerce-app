package com.example.ecommerce

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerce.Adaptor.BrandsAdapter
import com.example.ecommerce.databinding.ActivityDashboardBinding
import com.example.ecommerce.viewmodel.MainViewModel

class Dashboard_activity : AppCompatActivity() {
    private val viewModel:MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var binding: ActivityDashboardBinding
    private val brandsAdapter=BrandsAdapter(mutableListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView.adapter=brandsAdapter
        binding.progressBarCat.visibility= View.VISIBLE
        viewModel.brands.observe(this)
        {

            data -> brandsAdapter.updatedata(data)
            Log.d("fire",data.toString())
            binding.progressBarCat.visibility=View.GONE
        }
        viewModel.loadBrands()
    }
}