package com.example.uberchicks.ui.categorylist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentCategoryListBinding
import com.example.uberchicks.domain.ProductUiModel
import com.example.uberchicks.ui.productlist.ProductListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class CategoryListFragment : Fragment(R.layout.fragment_category_list),
    ProductListAdapter.OnItemClickListener {

    private val viewModel: CategoryListViewModel by viewModels<CategoryListViewModel>()
    private lateinit var binding: FragmentCategoryListBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryListBinding.bind(view)

        val categoryListAdapter = CategoryListAdapter(this)

        binding.recyclerViewCategoryList.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = categoryListAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.countAndPrice.collect { countPricePair ->
                val buttonCheckout = binding.buttonCheckoutCategoryList
                if (countPricePair.first > 0  &&  countPricePair.second > 0.0) {
                    buttonCheckout.apply {
                        visibility = View.VISIBLE
                        text = "${countPricePair.first} orders Kshs ${countPricePair.second}"
                        setOnClickListener {
                            Timber.i("Navigating to Cart")
                            val action = CategoryListFragmentDirections.actionCategoryListFragmentToCartFragment()
                            findNavController().navigate(action)
                        }
                    }
                } else {
                    buttonCheckout.visibility = View.GONE
                }
            }
        }
//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            //Get categories
//            val categoryList = viewModel.getCategories().map {
//                it.asDomainObject()
//            }
//            //If product.id is not in the database, then put the default UI models, else update the quantity
//            categoryListAdapter.submitList(categoryList)
//        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.categoriesFlow.collect{
                categoryListAdapter.submitList(it)
            }
        }


//        viewModel.categoriesUiModel.observe(viewLifecycleOwner) {
//            categoryListAdapter.submitList(it)
//        }
    }

    override fun onItemClick(productUiModel: ProductUiModel) {
        val action =
            CategoryListFragmentDirections.actionGlobalAddCartDialogFragment(productUiModel)
        findNavController().navigate(action)
    }

    override fun onRemoveClick(productUi: ProductUiModel) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.removeFromCart(productUi)
        }
    }
}