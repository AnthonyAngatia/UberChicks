package com.example.uberchicks.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import com.example.uberchicks.R
import retrofit2.Retrofit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_registration)

        var email=findViewById(R.id.editRegTextEmail) as EditText
        var password=findViewById(R.id.editRegTextPassword) as EditText
        var first_name=findViewById(R.id.editRegTextFirstName) as EditText
        var last_name=findViewById(R.id.editRegTextLastName) as EditText
        var btn_signup=findViewById(R.id.btn_signup) as Button

        btn_signup.setOnClickListener{
            //first clear email and password edit texts
            val email=email.text.toString().trim()
            val first_name=first_name.text.toString().trim()
            val last_name=last_name.text.toString().trim()
            val password=password.text.toString().trim()

        }
        DataTransferObjects.instance.createUser
    }
}