package com.example.uberchicks.ui.productlist

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentProductListBinding
import com.example.uberchicks.domain.Product
import com.example.uberchicks.network.asDomainObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list) {

    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductListBinding.bind(view)
        //TODO: Retrieve the category id and perform a query for the products
        val productAdapter = ProductListAdapter()

        binding.recyclerViewProductList.apply {
            adapter = productAdapter
            //TODO: Find a way to make the span count adaptive to the size of the screen
            layoutManager = GridLayoutManager(context,2)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            //Fetch list of products form the backend
            val productList: List<Product> = viewModel.getProducts().map { productDto ->
                productDto.asDomainObject()
            }
            productAdapter.submitList(productList)

        }


    }
}