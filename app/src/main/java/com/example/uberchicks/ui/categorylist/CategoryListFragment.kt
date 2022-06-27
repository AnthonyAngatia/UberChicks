package com.example.uberchicks.ui.categorylist

import android.os.Bundle
import android.view.MenuItem
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
import timber.log.Timber


@AndroidEntryPoint
class CategoryListFragment : Fragment(R.layout.fragment_category_list),
    ProductListAdapter.OnItemClickListener {

    private val viewModel: CategoryListViewModel by viewModels<CategoryListViewModel>()
    private lateinit var binding: FragmentCategoryListBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCategoryListBinding.bind(view)

        setupToolBar()


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
            viewModel.categoriesFlow.collect {
                categoryListAdapter.submitList(it)
            }
        }
        setupCheckoutButton()


    }

    private fun setupCheckoutButton() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.countAndPrice.collect { countPricePair ->
                val buttonCheckout = binding.buttonCheckoutCategoryList
                if (countPricePair.first > 0 && countPricePair.second > 0.0) {
                    buttonCheckout.apply {
                        visibility = View.VISIBLE
                        text = "${countPricePair.first} orders Kshs ${countPricePair.second}"
                        setOnClickListener {
                            Timber.i("Navigating to Cart")
                            val action =
                                CategoryListFragmentDirections.actionCategoryListFragmentToCartFragment()
                            findNavController().navigate(action)
                        }
                    }
                } else {
                    buttonCheckout.visibility = View.GONE
                }
            }
        }
    }


    private fun setupToolBar() {
        binding.toolBarCategorylist.toolbarHome.apply {
            title = "Uberchicks"

            setNavigationIcon(R.drawable.toolbar_image)

            setNavigationOnClickListener {
                //Navigate to user profile
                //If User has not signed in yet, navigate to to sign up sign in page
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    viewModel.isLoggedIn.collect { isLoggedIn ->
                        Timber.i("Login status: $isLoggedIn")
                        if (isLoggedIn) {
                            //Navigate to profile page
                        } else {
                            //Navigate to Registration/ Login page
                        }

                    }
                }
            }

            //Inflate menu
            inflateMenu(R.menu.menu)

            //Show clearcart menu item
            val clearCartMenuItem: MenuItem = menu.findItem(R.id.menuitem_clear_cart)
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.isCartEmpty.collect{ isCartEmpty->
                    clearCartMenuItem.isVisible = !isCartEmpty
                }
            }

            //Menu click listener
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menuitem_logout -> {
                        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                            //TODO: Clear user preference
                        }
                        true
                    }
                    R.id.menuitem_clear_cart -> {
                        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                            viewModel.clearCart()
                        }
                        true
                    }
                    else -> false
                }
            }

        }
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