package com.example.uberchicks.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id:Int,
    val productName: String,
    val productPrice:Double,
    val priceDescription:String,
    val quantityRemaining:Int,
    val imageUrl:String
):Parcelable

@Parcelize
data class Category(
    val id:Int,
    val categoryName:String,
    val products:List<Product>
): Parcelable