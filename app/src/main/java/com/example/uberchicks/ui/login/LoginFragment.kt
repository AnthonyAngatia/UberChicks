package com.example.uberchicks.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.uberchicks.R
import com.example.uberchicks.ui.productlist.ProductListAdapter


class LoginFragment : Fragment(R.layout.fragment_login), LoginAdapter.OnItemClickListener {
    private lateinit var binding: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=LoginFragmentBinding.bind(view)

    }
}