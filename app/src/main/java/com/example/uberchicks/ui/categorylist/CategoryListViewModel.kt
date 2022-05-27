package com.example.uberchicks.ui.categorylist

import androidx.lifecycle.ViewModel
import com.example.uberchicks.Repository
import com.example.uberchicks.network.CategoryDto
import com.example.uberchicks.network.ProductDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    suspend fun getCategories(): List<CategoryDto> {
        try {
//            repository.getCategoriesWithProducts()
        } catch (e: Exception) {

        }
        val prod1 = ProductDto(
            1, "eggs", 320.0, 50.0, "Ksh 320 per egss", ""
        )
        val prod2 = ProductDto(
            2, "Broilers", 500.0, 50.0, "Ksh 320 per hen", ""
        )
        val prod3 = ProductDto(
            3, "Turkey", 450.0, 50.0, "Ksh 320 per turkey", ""
        )

        return listOf(
            CategoryDto(1, "Poultry", listOf(prod1, prod2,prod3, prod2, prod1, prod3)),
            CategoryDto(2, "Vegetables", listOf(prod1, prod2)),
            CategoryDto(3, "Animal Products", listOf(prod1, prod2,prod3))
        )
    }

}