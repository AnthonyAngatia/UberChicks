package com.example.uberchicks

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.uberchicks.domain.Product

/**
 * Deprecated, Replaced with room database
 * */
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

/**
 *
 * Manage user login
 *
 **/
const val USER_PREFERENCES = "user_preferences"

data class UserPreference(
    val userId: String,
    val firstName:String,
    val lastName:String,
    val phoneNumber:String,
    val userEmail: String,
    val token: String,
    val isLoggedIn:Boolean
)

object UserPreferenceKeys{
    val USER_ID = stringPreferencesKey("user_id")
    val FIRST_NAME = stringPreferencesKey("first_name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val PHONE_NUMBER = stringPreferencesKey("phone_number")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val TOKEN = stringPreferencesKey("token")
    val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
}