package com.example.uberchicks.network

import com.example.uberchicks.domain.Category
import com.example.uberchicks.domain.Product
import com.squareup.moshi.Json

/**
 * Product
 * */
data class ProductDto(
    val id: Int,
    @Json(name = "product_name")
    val productName: String,
    @Json(name = "product_price")
    val productPrice: Double,
    @Json(name = "product_discount")
    val productDiscount: Double?,
    @Json(name = "price_description")
    val priceDescription: String,
    @Json(name = "image_url")
    val imageUrl: String
)

/**
 * Extension function of ProductDto to convert it into a domain object
 * */
fun ProductDto.asDomainObject(): Product {
    return Product(
        id = this.id,
        productName = this.productName,
        productPrice = this.productPrice,
        productDiscount = this.productDiscount,
        priceDescription = this.priceDescription,
        imageUrl = this.imageUrl
    )
}

/**
 * Category
 **/
data class CategoryDto(
    val id: Int,
    @Json(name = "category_name")
    val categoryName: String,
    @Json(name = "products")
    val products: List<ProductDto>
)

fun CategoryDto.asDomainObject(): Category {
    return Category(
        id = this.id,
        categoryName = this.categoryName,
        products = this.products.map { productDto ->
            productDto.asDomainObject()
        }
    )
}
