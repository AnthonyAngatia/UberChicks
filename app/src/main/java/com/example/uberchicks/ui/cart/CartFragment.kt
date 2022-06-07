package com.example.uberchicks.ui.cart

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentCartFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment:Fragment(R.layout.fragment_cart_fragment) {

    private val viewModel:CartViewModel by viewModels()
    private lateinit var binding:FragmentCartFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartFragmentBinding.bind(view)

        val cartAdapter = CartAdapter()

        binding.cartItemsRecyclerView.apply {
            adapter = cartAdapter
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.cartItems.collect{cartItems->
                //Submit list to adapter
                cartAdapter.submitList(cartItems)


            }
        }




    }
}