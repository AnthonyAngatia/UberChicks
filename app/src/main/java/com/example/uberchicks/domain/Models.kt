package com.example.uberchicks.domain

import android.os.Parcelable
import com.example.uberchicks.database.CartDatabaseModel
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

data class Cart(
    val product: Product,
    val quantity: Int
)

@Parcelize
data class ProductUiModel(
    val id: Int,
    val productName: String,
    val productPrice: Double,
    val productDiscountedPrice: Double?,
    val priceDescription: String,
    val imageUrl: String,
    var quantity: Int?
):Parcelable

fun ProductUiModel.asDomainModel():Product{
    return Product(
        id = this.id,
        productName = this.productName,
        productPrice = this.productPrice,
        productDiscount = this.productDiscountedPrice,
        priceDescription = this.priceDescription,
        imageUrl = this.imageUrl
    )
}

data class CategoryUi(
    val id: Int,
    val categoryName: String,
    val products: List<ProductUiModel>
)

fun List<Product>.asProductUiModelList(cartItemsList:List<CartDatabaseModel>):List<ProductUiModel>{
    val productUiList = mutableListOf<ProductUiModel>()
    for(product in this){
        val temp = ProductUiModel(
            product.id,
            product.productName,
            product.productPrice,
            product.productDiscount,
            product.priceDescription,
            product.imageUrl,
            null
        )
        for (item in cartItemsList){
            if(product.id == item.productId){
                temp.quantity = item.quantity
            }
        }
        productUiList.add(temp)
    }
    return productUiList
}

fun List<Category>.asCategoryUiModelList(cartItemsList: List<CartDatabaseModel>): List<CategoryUi>{
    val categoryUiList = mutableListOf<CategoryUi>()
    for(category in this){
        val temp = CategoryUi(
            category.id,
            category.categoryName,
            category.products.asProductUiModelList(cartItemsList)
        )
        categoryUiList.add(temp)
    }
    return categoryUiList
}




/**
 * This might introduce a bug in the part in attribute quantyty
 * *Not in use currently
 * */
fun Product.asProductUiModel(): ProductUiModel {
        return ProductUiModel(
            this.id,
            this.productName,
            this.productPrice,
            this.productDiscount,
            this.priceDescription,
            this.imageUrl,
            null
        )
    }

//
//fun Category.asCategoryUiModel(cartDatabaseModel: CartDatabaseModel): CategoryUi {
//    return CategoryUi(
//        this.id,
//        this.categoryName,
//        this.products.map { product ->
//            product.asProductUiModel(cartDatabaseModel)
//        }
//    )
//
//}
