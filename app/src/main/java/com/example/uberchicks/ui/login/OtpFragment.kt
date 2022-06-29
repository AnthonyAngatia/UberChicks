package com.example.uberchicks.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentOtpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpFragment: Fragment(R.layout.fragment_otp) {

    private lateinit var binding:FragmentOtpBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOtpBinding.bind(view)

        binding.toolBarOtp.toolbarHome.apply {
            title = "Uberchicks"

            setNavigationIcon(R.drawable.ic_baseline_home_24)

            setNavigationOnClickListener {
                //Navigate back to the previous fragment or Navigate to categorylist
                findNavController().popBackStack()
            }
        }


    }
}