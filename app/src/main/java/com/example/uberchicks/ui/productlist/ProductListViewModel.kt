package com.example.uberchicks.ui.productlist

import androidx.lifecycle.ViewModel
import com.example.uberchicks.Repository
import com.example.uberchicks.domain.ProductUiModel
import com.example.uberchicks.network.ProductDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    suspend fun getProducts(): List<ProductDto> {
        try {
//            repository.getProducts().body()!!.productList

        } catch (e: Exception) {

        }
        val productDtoList = listOf<ProductDto>(
            ProductDto(
                1, "eggs", 320.0, 50.0, "Ksh 320 per egss", ""
            )
        )
        return productDtoList
    }

    suspend fun removeFromCart(productUi: ProductUiModel) {
        repository.removeFromCart(productUi)
    }

}