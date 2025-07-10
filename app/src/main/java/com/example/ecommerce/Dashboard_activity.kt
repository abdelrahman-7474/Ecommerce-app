package com.example.ecommerce

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ecommerce.Adaptor.BrandsAdapter
import com.example.ecommerce.Adaptor.ProductsAdapter
import com.example.ecommerce.Adaptor.SliderAdaptor
import com.example.ecommerce.databinding.ActivityDashboardBinding
import com.example.ecommerce.models.Products
import com.example.ecommerce.models.SliderModel
import com.example.ecommerce.viewmodel.MainViewModel

class Dashboard_activity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var binding: ActivityDashboardBinding
    private val brandsAdapter = BrandsAdapter(mutableListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = brandsAdapter
        binding.progressBarCat.visibility = View.VISIBLE
        binding.progressBarBanner.visibility=View.VISIBLE
        binding.progressBarRecommendation.visibility=View.VISIBLE
        viewModel.brands.observe(this)
        {

                data ->
            brandsAdapter.updatedata(data)
            Log.d("fire", data.toString())
            binding.progressBarCat.visibility = View.GONE
        }
        viewModel.sliders.observe(this)
        {
            data ->
            setupBanners(data)
            Log.d("fire", data.toString())
            binding.progressBarBanner.visibility = View.GONE

        }
        viewModel.products.observe(this)
        {
            data->setrecommendation(data)
            Log.d("fire", data.toString())
            binding.progressBarRecommendation.visibility = View.GONE

        }
        viewModel.loadSliders()
        viewModel.loadBrands()
        viewModel.loadProducts()
    }

    private fun setupBanners(images: List<SliderModel>) {
        binding.viewpaggerslider.apply {
            adapter = SliderAdaptor(  images,  this)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            (getChildAt( 0) as? RecyclerView)?.overScrollMode =
                RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(CompositePageTransformer().apply {
                addTransformer( MarginPageTransformer( 40))
            })
        }
        binding.dotindicator.apply {
            visibility = if (images.size > 1) View.VISIBLE else View.GONE
            if (images.size > 1) attachTo(viewPager2 = binding.viewpaggerslider)
        }
    }
    private fun setrecommendation(items: MutableList<Products>)
    {
        binding.recyclerViewRecommendatioin.layoutManager=GridLayoutManager(this,2)
    binding.recyclerViewRecommendatioin.adapter=ProductsAdapter(items)

    }
}