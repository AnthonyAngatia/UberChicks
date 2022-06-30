package com.example.uberchicks.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uberchicks.R
import com.example.uberchicks.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)


        //Setting up toolbar
        binding.toolBarLogin.toolbarHome.apply {
            title = "Uberchicks"

            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)

            setNavigationOnClickListener {
                //NAvigate back to the previous fragment
                findNavController().popBackStack()
            }
        }
        binding.buttonCreateAccount.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}