package com.example.uberchicks

import com.example.uberchicks.network.CategoryListResponse
import com.example.uberchicks.network.ProductDto
import com.example.uberchicks.network.ProductListResponse
import com.example.uberchicks.network.UberChicksApiService
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val uberChicksApiService: UberChicksApiService
) {


    suspend fun getProducts(): Response<ProductListResponse> {
        val productDtoList = listOf<ProductDto>(
            ProductDto(
                1, "eggs", 320.0, 50.0, "Ksh 320 per egss", ""
            )
        )

        return uberChicksApiService.getProduct()
//        return productDtoList
    }

    suspend fun getCategoriesWithProducts():Response<CategoryListResponse> {
        return uberChicksApiService.getCategoriesWithProducts()
    }
}