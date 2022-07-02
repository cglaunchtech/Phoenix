package com.example.sportssocial.ui.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.sportssocial.R
import kotlin.math.sign

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Page Elements
        val signInButton : View = view.findViewById(R.id.signInButton)
        val createUpButton : View = view.findViewById(R.id.signUpButton)
        val viewerButton : View = view.findViewById(R.id.viewerButton)
        val signUpButton : View = view.findViewById(R.id.signUpButton)

        //Page Actions
        signInButton.setOnClickListener {
            signInButton.findNavController().navigate(R.id.action_loginFragment_to_homepageFragment)
        }

        viewerButton.setOnClickListener {
            viewerButton.findNavController().navigate(R.id.action_loginFragment_to_homepageFragment)
        }

        signUpButton.setOnClickListener {
            signUpButton.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        return view
    }

}