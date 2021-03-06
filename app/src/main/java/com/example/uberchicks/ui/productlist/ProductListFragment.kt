package com.example.uberchicks.ui.productlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentProductListBinding
import com.example.uberchicks.domain.ProductUiModel
import com.example.uberchicks.domain.asProductUiModel
import com.example.uberchicks.network.asDomainObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment(R.layout.fragment_product_list), ProductListAdapter.OnItemClickListener {

    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductListBinding.bind(view)
        //TODO: Retrieve the category id and perform a query for the products
        val productAdapter = ProductListAdapter(this)

        binding.recyclerViewProductList.apply {
            adapter = productAdapter
            //TODO: Find a way to make the span count adaptive to the size of the screen
            layoutManager = GridLayoutManager(context,2)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            //Fetch list of products form the backend
            val productList: List<ProductUiModel> = viewModel.getProducts().map { productDto ->
                productDto.asDomainObject().asProductUiModel()
            }
            productAdapter.submitList(productList)

        }


    }

    override fun onItemClick(productUi: ProductUiModel) {
        val action = ProductListFragmentDirections.actionGlobalAddCartDialogFragment(productUi)
        findNavController().navigate(action)
    }

    override fun onRemoveClick(productUi: ProductUiModel) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.removeFromCart(productUi)
        }
    }
}