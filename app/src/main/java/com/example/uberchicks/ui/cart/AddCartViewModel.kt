package com.example.uberchicks.ui.cart

import androidx.lifecycle.ViewModel
import com.example.uberchicks.CartPreferences
import com.example.uberchicks.Repository
import com.example.uberchicks.domain.Cart
import com.example.uberchicks.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AddCartViewModel @Inject constructor(private val repository: Repository):ViewModel() {


    suspend fun addToCart(product: Product, input: Int) {
        repository.insertToCart(Cart(product,input))
    }
}