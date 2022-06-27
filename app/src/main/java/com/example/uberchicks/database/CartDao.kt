package com.example.uberchicks.database

import androidx.room.*
import com.example.uberchicks.domain.Cart
import com.example.uberchicks.domain.Product
import com.example.uberchicks.domain.ProductUiModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    // OnConflictStrategy works as my @Update query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(databaseModel: CartDatabaseModel)

    @Query("SELECT * FROM CART_TABLE")
    fun getAllCartItems(): Flow<List<CartDatabaseModel>>

    @Query("SELECT * FROM CART_TABLE WHERE productId = :key")
    fun get(key: Int): Flow<CartDatabaseModel>

    @Query("DELETE FROM cart_table")
    suspend fun clearCart()

    @Delete
    suspend fun deleteItem(cartDatabaseModel: CartDatabaseModel)

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


fun CartDatabaseModel.asProductUiModel():ProductUiModel{
    return ProductUiModel(
        this.productId,
        this.productName,
        this.productPrice,
        this.productDiscount,
        this.priceDescription,
        this.imageUrl,
        this.quantity!!
    )
}
fun ProductUiModel.asCartDatabaseModel(): CartDatabaseModel {
    return CartDatabaseModel(
            this.id,
            this.productName,
            this.productPrice,
            this.productDiscountedPrice,
            this.priceDescription,
            this.imageUrl,
        this.quantity!!
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