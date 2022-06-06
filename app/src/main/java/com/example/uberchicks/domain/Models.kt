package com.example.uberchicks.domain

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val productName: String,
    val productPrice: Double,
    val productDiscount: Double?,
    val priceDescription: String,
    val imageUrl: String
) : Parcelable

@Parcelize
data class Category(
    val id: Int,
    val categoryName: String,
    val products: List<Product>
) : Parcelable

data class Cart(
    val product: Product,
    val quantity:Int
)