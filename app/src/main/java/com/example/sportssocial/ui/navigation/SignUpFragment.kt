package com.example.sportssocial.ui.navigation

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.sportssocial.R

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        //Page Elements
        val cancelButton : View = view.findViewById(R.id.cancelButton)
        val submitButton : View = view.findViewById(R.id.submitButton)

        //TODO: SET SPINNER INFORMATION

        //Page Actions
        cancelButton.setOnClickListener {
            //TODO:
            Toast.makeText(requireContext(), "SignUp Cancelled", Toast.LENGTH_LONG).show()
            cancelButton.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        submitButton.setOnClickListener {
            //TODO: CHECK FIELDS FOR APPROPRIATE INPUT
            //TODO: DISABLE BUTTON IF FORM EMPTY
            Toast.makeText(requireContext(), "Welcome to Sports Social!", Toast.LENGTH_LONG).show()
            submitButton.findNavController().navigate(R.id.action_loginFragment_to_containerFragment)
        }


        return view
    }

}