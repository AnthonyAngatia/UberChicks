package com.example.uberchicks.ui.categorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.uberchicks.Repository
import com.example.uberchicks.network.CategoryDto
import com.example.uberchicks.network.ProductDto
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val categoriesFlow = repository.categoriesUiFlow
    val categoriesUiModel = categoriesFlow.asLiveData()

//    suspend fun getCategories(): List<CategoryDto> {
////        try {
////            repository.getCategoriesWithProducts()
////        } catch (e: Exception) {
////
////        }
//        return repository.categoriesUiFlow
//    }

}