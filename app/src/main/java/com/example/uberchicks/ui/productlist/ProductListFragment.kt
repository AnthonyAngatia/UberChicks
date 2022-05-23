package com.example.uberchicks.ui.productlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.uberchicks.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment: Fragment(R.layout.fragment_product_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO: Retrieve the category id and perform a query for the products

    }
}