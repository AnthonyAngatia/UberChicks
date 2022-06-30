package com.example.uberchicks.network

import com.squareup.moshi.Json
import retrofit2.Response

interface UberChicksApiService {
    companion object {
        const val BASE_URL = "http://scan4pads.herokuapp.com/api/v1/"

    }

    suspend fun getProduct(): Response<ProductListResponse>
    suspend fun getCategoriesWithProducts(): Response<CategoryListResponse>

}

data class ProductListResponse(
    @Json(name = "data") val productList: List<ProductDto>
)

data class CategoryListResponse(
    @Json(name = "data") val categoryList: List<CategoryDto>
)

//will need to be changed later-> there is no userList
data class UserResponse(
    @Json(name = "user") val userList: List<UserDto>
)