package com.example.ecommerce.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ecommerce.models.BrandModel
import com.example.ecommerce.models.Products
import com.example.ecommerce.models.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {
private val firebaseDatabase=FirebaseDatabase.getInstance()
    private val _brands= MutableLiveData<MutableList<BrandModel>>()
    private val _sliders= MutableLiveData<MutableList<SliderModel>>()
    private val _products= MutableLiveData<MutableList<Products>>()


    val brands:LiveData<MutableList<BrandModel>> get()=_brands
    val sliders:LiveData<MutableList<SliderModel>> get()=_sliders
    val products:LiveData<MutableList<Products>> get()=_products


    fun loadBrandes()
    {
        val ref=firebaseDatabase.getReference("Category")
        ref.addValueEventListener(object :ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<BrandModel>()
                for(childSnapshot in snapshot.children )
                {
                    childSnapshot.getValue(BrandModel::class.java)?.let {
                        list.add(it)
                    }
                }
                _brands.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error: ${error.message}")
            }

        })
    }
    fun loadsliders()
    {
        val ref=firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object :ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<SliderModel>()
                for(child in snapshot.children)
                {
                    child.getValue(SliderModel::class.java)?.let {
                        list.add(it)
                    }
                }
                _sliders.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error: ${error.message}")
            }

        })

    }
    fun laodproducts()
    {
        val ref=firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<Products>()
                for(child in snapshot.children)
                {
                    child.getValue(Products::class.java)?.let {
                        list.add(it)
                    }
                }
                _products.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Error: ${error.message}")
            }

        })
    }

}