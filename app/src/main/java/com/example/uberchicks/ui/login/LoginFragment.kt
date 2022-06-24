package com.example.uberchicks.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.uberchicks.R
import com.example.uberchicks.ui.registration.RegistrationFragment


class LoginFragment : Fragment(R.layout.fragment_login), LoginAdapter.OnItemClickListener {
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    val MIN_PASSWORD_LENGTH = 6

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding=LoginFragmentBinding.bind(view)
        viewInitializations()

    }

    fun viewInitializations() {
        etEmail = findViewById(R.id.editLoginEmail)
        etPassword = findViewById(R.id.editLoginPassword)
    }

    fun validateInput(): Boolean {
        if (etEmail.text.toString() == "") {
            etEmail.error = "Enter Email"
            return false
        }
        if (etPassword.text.toString() == "") {
            etPassword.error = "Enter Password"
            return false
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.text.toString())) {
            etEmail.error = "Please Enter Valid Email"
            return false
        }

        // checking minimum password Length
        if (etPassword.text.length < MIN_PASSWORD_LENGTH) {
            etPassword.error =
                "Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters"
            return false
        }
        return true
    }

    fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun performSignIn(v: View) {
        if (validateInput()) {
            //input is valid, send response
            val email = etEmail!!.text.toString()
            val password = etPassword!!.text.toString()

            //provide a success message after this
            //make API call here
        }
    }

    fun goToSignUp(v: View) {
        //open registration activity
        val registrationIntent = Intent(this, RegistrationFragment::class)
        startActivity(registrationIntent)
    }
}