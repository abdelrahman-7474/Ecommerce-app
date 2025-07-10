package com.example.ecommerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.ecommerce.Repository.MainRepository
import com.example.ecommerce.models.BrandModel
import com.example.ecommerce.models.SliderModel

class MainViewModel() : ViewModel() {
    private val  repository=MainRepository() // need to be paramter
    val brands:LiveData<MutableList<BrandModel>> =repository.brands
    val sliders: LiveData<MutableList<SliderModel>> =repository.sliders


    fun loadBrands() =repository.loadBrandes()
    fun loadSliders()=repository.loadsliders()

}