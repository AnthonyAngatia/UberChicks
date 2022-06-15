package com.example.uberchicks.ui.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.uberchicks.R
import com.example.uberchicks.database.asProductUiModel
import com.example.uberchicks.databinding.FragmentCartFragmentBinding
import com.example.uberchicks.domain.ProductUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart_fragment), CartAdapter.onCartCLickListener {

    private val viewModel: CartViewModel by viewModels()
    private lateinit var binding: FragmentCartFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartFragmentBinding.bind(view)

        binding.toolBarCart.toolbarHome.apply {
            title = "Cart"

            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener{
                //NAvigate back to the previous fragment
            }
        }


        val cartAdapter = CartAdapter(this)

        binding.cartItemsRecyclerView.apply {
            adapter = cartAdapter
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.cartItems.collect { cartItems ->
                //Submit list to adapter
                val productUiModels = cartItems.map {
                    it.asProductUiModel()
                }
                if (productUiModels.isEmpty()) {
                    //TODO: Navigate back
//                    val action = CartFragmentDirections.actionCartFragmentToCategoryListFragment()
//                    findNavController().navigate(action)
                    cartAdapter.submitList(productUiModels)

                } else {
                    cartAdapter.submitList(productUiModels)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.total.collect {
                binding.textViewSummaryTotal.text = it.toString()
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onCartClickListener(productUiModel: ProductUiModel) {
        val action =
            CartFragmentDirections.actionCartFragmentToEditCartDialogFragment(productUiModel)
        findNavController().navigate(action)
    }
}