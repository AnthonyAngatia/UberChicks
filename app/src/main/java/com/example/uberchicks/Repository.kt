package com.example.uberchicks

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.uberchicks.database.CartDao
import com.example.uberchicks.database.CartDatabaseModel
import com.example.uberchicks.database.asCartDatabaseModel
import com.example.uberchicks.database.asDatabaseModel
import com.example.uberchicks.domain.*
import com.example.uberchicks.network.*
import kotlinx.coroutines.flow.*
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val uberChicksApiService: UberChicksApiService,
    private val dataStore: DataStore<Preferences>,
    private val cartDao: CartDao
) {
    //TODO:Change whatever is returned from the API service to a flow
    val categoriesWithProductsFlow = flowOf(
        getCategoriesWithProducts().map { it.asDomainObject() }
    )
    val cartItemsFLow = cartDao.getAllCartItems()

    val countAndPrice: Flow<Pair<Int, Double>> = cartItemsFLow.map { cartItems ->
        val noOfItems = cartItems.size
        var price = 0.0;
        for (item in cartItems) {
            price += getPrice(item)
        }
        Pair(noOfItems, price)
    }


    private fun getPrice(item: CartDatabaseModel): Double {
        return if (item.productDiscount != null && item.productDiscount > 0.0) {
            item.quantity * item.productDiscount
        } else {
            item.quantity * item.productPrice
        }
    }

    val categoriesUiFlow: Flow<List<CategoryUi>> = combine(
        categoriesWithProductsFlow,
        cartItemsFLow
    ) { categoriesWithProducts: List<Category>, cartItems: List<CartDatabaseModel> ->
        return@combine categoriesWithProducts.asCategoryUiModelList(cartItems)
    }


    val cartPreferencesFlow: Flow<CartPreferences> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            Timber.e("IOException when emitting cart preferences as a flow")
            emit(emptyPreferences())
        }
    }.map { preferences ->
        val cartPreference = mapCartPreference(preferences)
        cartPreference
    }

    private fun mapCartPreference(preferences: Preferences): CartPreferences {
        val product = mapProduct(preferences)
        val quantity = preferences[PreferenceKeys.QUANTITY] ?: 1
        return CartPreferences(product, quantity)
    }

    private fun mapProduct(preferences: Preferences): Product {
        val id = preferences[PreferenceKeys.ID] ?: 0
        val productName = (preferences[PreferenceKeys.PRODUCT_NAME] ?: ' ').toString()
        val productPrice = preferences[PreferenceKeys.PRODUCT_PRICE] ?: 0.0
        val productDiscount = if (preferences[PreferenceKeys.PRODUCT_DISCOUNT] == 0.0) {
            null
        } else {
            preferences[PreferenceKeys.PRODUCT_DISCOUNT]
        }
        val priceDescription = (preferences[PreferenceKeys.PRICE_DESCRIPTION] ?: ' ').toString()
        val imageUrl = (preferences[PreferenceKeys.IMAGE_URL]).toString()
        val product =
            Product(id, productName, productPrice, productDiscount, priceDescription, imageUrl)
        return product
    }

    suspend fun getProducts(): Response<ProductListResponse> {
        val productDtoList = listOf<ProductDto>(
            ProductDto(
                1, "eggs", 320.0, 50.0, "Ksh 320 per egss", ""
            )
        )

        return uberChicksApiService.getProduct()
//        return productDtoList
    }

    fun getCategoriesWithProducts(): List<CategoryDto> {
        val prod1 = ProductDto(
            1, "eggs", 320.0, 0.0, "Ksh 320 per egss", ""
        )
        val prod2 = ProductDto(
            2, "Broilers", 500.0, 0.0, "Ksh 320 per hen", ""
        )
        val prod3 = ProductDto(
            3, "Turkey", 450.0, 50.0, "Ksh 320 per turkey", ""
        )

        return listOf(
            CategoryDto(1, "Poultry", listOf(prod1, prod2, prod3, prod2, prod1, prod3)),
            CategoryDto(2, "Vegetables", listOf(prod1, prod3)),
            CategoryDto(3, "Animal Products", listOf( prod2, prod3, prod1))
        )
//        return uberChicksApiService.getCategoriesWithProducts()
    }

    suspend fun addToCart(cartPreferences: CartPreferences) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.QUANTITY] = cartPreferences.quantity

            preferences[PreferenceKeys.ID] = cartPreferences.product.id
            preferences[PreferenceKeys.PRODUCT_NAME] = cartPreferences.product.productName
            preferences[PreferenceKeys.PRODUCT_PRICE] = cartPreferences.product.productPrice
            preferences[PreferenceKeys.PRODUCT_DISCOUNT] =
                cartPreferences.product.productDiscount ?: 0.0
            preferences[PreferenceKeys.PRICE_DESCRIPTION] = cartPreferences.product.priceDescription
            preferences[PreferenceKeys.IMAGE_URL] = cartPreferences.product.imageUrl

        }
    }

    suspend fun insertToCart(item: Cart) {
        val cartDatabaseModel = item.asDatabaseModel()
        Timber.i("Insert into cart method:${item.toString()}")
        cartDao.insert(cartDatabaseModel)
    }

    suspend fun removeFromCart(productUiModel: ProductUiModel) {
        Timber.i("Delete from cart at repository")
        cartDao.deleteItem(productUiModel.asCartDatabaseModel())
    }
}