package com.example.uberchicks.ui.checkout

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentCheckoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : Fragment(R.layout.fragment_checkout) {
    private lateinit var binding: FragmentCheckoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckoutBinding.bind(view)

        binding.toolBarCheckout.toolbarHome.apply {
            title = "Uberchicks Payment"

            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener {
                //NAvigate back to the previous fragment
                findNavController().popBackStack()
            }
        }

        binding.tingWebView.loadUrl("https://developer.tingg.africa/checkout-staging/v2/testers/default/TestSandboxRedirect.php")
//binding.tingWebView.loadUrl("https://www.google.com")
        binding.tingWebView.webViewClient = WebViewClient()
        binding.tingWebView.settings.javaScriptEnabled = true

    }
}