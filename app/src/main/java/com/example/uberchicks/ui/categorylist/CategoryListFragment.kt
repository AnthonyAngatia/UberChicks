package com.example.uberchicks.ui.categorylist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.uberchicks.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment:Fragment(R.layout.fragment_category_list) {

    private val viewModel:CategoryListViewModel by viewModels<CategoryListViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}