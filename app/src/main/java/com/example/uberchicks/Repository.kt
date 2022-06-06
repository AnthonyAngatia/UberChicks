package com.example.uberchicks

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.uberchicks.domain.Product
import com.example.uberchicks.network.CategoryListResponse
import com.example.uberchicks.network.ProductDto
import com.example.uberchicks.network.ProductListResponse
import com.example.uberchicks.network.UberChicksApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val uberChicksApiService: UberChicksApiService,
    private val dataStore: DataStore<Preferences>
) {

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

    suspend fun getCategoriesWithProducts(): Response<CategoryListResponse> {
        return uberChicksApiService.getCategoriesWithProducts()
    }

    suspend fun addToCart(cartPreferences: CartPreferences) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.QUANTITY] = cartPreferences.quantity

            preferences[PreferenceKeys.ID] = cartPreferences.product.id
            preferences[PreferenceKeys.PRODUCT_NAME] = cartPreferences.product.productName
            preferences[PreferenceKeys.PRODUCT_PRICE] = cartPreferences.product.productPrice
            preferences[PreferenceKeys.PRODUCT_DISCOUNT] = cartPreferences.product.productDiscount?: 0.0
            preferences[PreferenceKeys.PRICE_DESCRIPTION] = cartPreferences.product.priceDescription
            preferences[PreferenceKeys.IMAGE_URL] = cartPreferences.product.imageUrl

        }
    }
}