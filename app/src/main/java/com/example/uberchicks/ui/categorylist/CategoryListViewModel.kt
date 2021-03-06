package com.example.uberchicks.ui.categorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.uberchicks.Repository
import com.example.uberchicks.UserRepository
import com.example.uberchicks.domain.ProductUiModel
import com.example.uberchicks.network.CategoryDto
import com.example.uberchicks.network.ProductDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(private val repository: Repository,
                                                private val userRepository: UserRepository) : ViewModel() {

    val isCartEmpty = repository.isCartEmpty

    val isLoggedIn = userRepository.isLoggedIn

    val countAndPrice: Flow<Pair<Int, Double>> = repository.countAndPrice

    val categoriesFlow = repository.categoriesUiFlow

    val categoriesUiModel = categoriesFlow.asLiveData()

    suspend fun deleteFromDatabase(productUiModel: ProductUiModel) {
        repository.removeFromCart(productUiModel)
    }

    suspend fun removeFromCart(productUi: ProductUiModel) {
        Timber.i("At remove from Cart CAtegory list")
        repository.removeFromCart(productUi)
    }

    suspend fun clearCart() {
        repository.clearCart()
    }

//    suspend fun getCategories(): List<CategoryDto> {
////        try {
////            repository.getCategoriesWithProducts()
////        } catch (e: Exception) {
////
////        }
//        return repository.categoriesUiFlow
//    }

}