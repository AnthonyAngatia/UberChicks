package com.example.uberchicks.ui.cart

import androidx.lifecycle.ViewModel
import com.example.uberchicks.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: Repository):ViewModel() {

    val cartItems = repository.cartItemsFLow
}