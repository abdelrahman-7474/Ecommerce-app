package com.example.ecommerce.models

import java.io.Serializable

data class Products(
    val colors:ArrayList<String> = ArrayList(),
    val description:String="",
    val oldPrice:Double=0.0,
    val picUrl:ArrayList<String> = ArrayList(),
    val price:Double=0.0,
    val rating:Double=0.0,
    val size:ArrayList<String> = ArrayList(),
    val title:String="",
    val numberInCart:Int=1
):Serializable
