package com.example.uberchicks.ui.cart.edit

import androidx.lifecycle.ViewModel
import com.example.uberchicks.Repository
import com.example.uberchicks.database.asCartDatabaseModel
import com.example.uberchicks.domain.Cart
import com.example.uberchicks.domain.Product
import com.example.uberchicks.domain.ProductUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditCartViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    suspend fun removeFromCart(productUi: ProductUiModel) {
        repository.removeFromCart(productUi)
    }

    suspend fun editCart(product: Product, quantity:Int){
        repository.insertToCart(Cart(product, quantity))
    }
}