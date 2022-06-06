package com.example.uberchicks

import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.uberchicks.domain.Product


const val CART_PREFERENCES = "cart_preferences"

data class CartPreferences(
    val product: Product,
    val quantity:Int
)

object PreferenceKeys{
    val QUANTITY = intPreferencesKey("quantity")
    val ID = intPreferencesKey("id")
    val PRODUCT_NAME = stringPreferencesKey("product_name")
    val PRODUCT_PRICE = doublePreferencesKey("product_price")
    val PRODUCT_DISCOUNT =  doublePreferencesKey("product_discount")
    val PRICE_DESCRIPTION = stringPreferencesKey("price_description")
    val IMAGE_URL = stringPreferencesKey("image_url")
}