package com.example.uberchicks.ui.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var binding:FragmentRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegistrationBinding.bind(view)

        binding.btnSignup.setOnClickListener {
            val action = RegistrationFragmentDirections.actionRegistrationFragmentToOtpFragment()
            findNavController().navigate(action)
        }
        //Setup bindings
        binding.toolBarRegistration.toolbarHome.apply {
            title = "Uberchicks"

            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener {
                //NAvigate back to the previous fragment
                findNavController().popBackStack()
            }
        }

    }
}