package com.example.uberchicks.database

import androidx.room.*
import com.example.uberchicks.domain.Cart
import com.example.uberchicks.domain.Product

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(databaseModel: CartDatabaseModel)

}

@Entity(tableName = "cart_table")
data class CartDatabaseModel(
    @PrimaryKey(autoGenerate = false)
    val productId: Int,
    val productName: String,
    val productPrice: Double,
    val productDiscount: Double?,
    val priceDescription: String,
    val imageUrl: String,
    val quantity: Int
)


fun CartDatabaseModel.asDomainModel(): Cart {
    return Cart(
        product = Product(
            this.productId,
            this.productName,
            this.productPrice,
            this.productDiscount,
            this.priceDescription,
            this.imageUrl
        ),
        quantity = this.quantity
    )
}

fun Cart.asDatabaseModel(): CartDatabaseModel {
    return CartDatabaseModel(
        this.product.id,
        this.product.productName,
        this.product.productPrice,
        this.product.productDiscount,
        this.product.priceDescription,
        this.product.imageUrl,
        this.quantity
    )
}