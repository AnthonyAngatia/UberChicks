package com.example.uberchicks.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
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

data class UserDetails(
    @PrimaryKey(autoGenerate=true)
    val userID:Int,

    @ColumnInfo(name="email")
    var email:String,

    @ColumnInfo(name="first_name")
    val first_name:String,

    @ColumnInfo(name="last_name")
    val last_name:String,

    val phoneNumber:Number,

    @ColumnInfo(name="password_text")
    val password:String
)

data class SignInBody(
    val email:String,
    val password:String
)