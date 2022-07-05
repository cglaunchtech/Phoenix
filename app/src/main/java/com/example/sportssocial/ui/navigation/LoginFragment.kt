package com.example.sportssocial.ui.navigation

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sportssocial.R
import com.example.sportssocial.ui.view.MainActivity
import com.example.sportssocial.ui.view.SignUp
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.sign

class LoginFragment : Fragment() {

    lateinit var auth: FirebaseAuth
    lateinit var signInButton: AppCompatButton
    lateinit var signUpButton: TextView
    lateinit var forgotLogin: TextView
    lateinit var loginEmailField: TextInputEditText
    lateinit var loginPasswordField: TextInputEditText
    lateinit var viewerButton: TextView
    lateinit var createUpButton : View
    lateinit var  passwordCheckbox : View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentuser = auth.currentUser
        if (currentuser != null) {

            view.findNavController().navigate(R.id.action_loginFragment_to_containerFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        auth = FirebaseAuth.getInstance()


        // Page Elements
        passwordCheckbox = view.findViewById(R.id.passwordCheckbox)
        signInButton = view.findViewById(R.id.signInButton)
        signUpButton = view.findViewById(R.id.signUpButton)
        forgotLogin = view.findViewById(R.id.forgotLogin)
        loginEmailField = view.findViewById(R.id.loginEmailField1)
        loginPasswordField = view.findViewById(R.id.loginPasswordField1)
        viewerButton = view.findViewById(R.id.viewerButton)

/*        //Page Actions
        signInButton.setOnClickListener {
            //TODO: ENSURE FORM IS FILLED
            //TODO: AUTHENTICATE LOGIN
            //TODO: CHECK CHECKBOX isCHECKED
            viewerButton.findNavController().navigate(R.id.action_loginFragment_to_containerFragment)
        }

        signUpButton.setOnClickListener {
            signUpButton.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }*/



        viewerButton.setOnClickListener {
            //TODO: SET TOOLBAR TO HIDDEN IN HOMEPAGE FRAGMENT
            //TODO: SET VIEWER MODE TO TRUE
            viewerButton.findNavController().navigate(R.id.action_loginFragment_to_containerFragment)
        }


        forgotLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (loginEmailField.text.toString().isNotEmpty()) {

                    auth.sendPasswordResetEmail(loginEmailField.text.toString())
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val builder = AlertDialog.Builder(requireContext())
                                builder.setMessage("Email Sent to ${loginEmailField.text.toString()}")
                                builder.setCancelable(true)
                                builder.setNegativeButton("OK", DialogInterface.OnClickListener
                                { dialog, which -> dialog.cancel() })
                                val alertDialog: AlertDialog = builder.create()
                                alertDialog.show()

                                Log.d(ContentValues.TAG, "Email sent.")
                            }
                        }
                } else {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setMessage("Please Enter a Valid Email Address")
                    builder.setCancelable(true)
                    builder.setNegativeButton("OK", DialogInterface.OnClickListener
                    { dialog, which -> dialog.cancel() })
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.show()

                }
            }
        })

        login()


        signUpButton.setOnClickListener {
            val myIntent = Intent(requireContext(), SignUp::class.java)
            signUpButton.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        return view
    }

    private fun login() {

        signInButton.setOnClickListener {
            if ((loginEmailField.text.toString()).isEmpty()) {
                loginEmailField.setHint("Please Enter a Valid Email Address")
                loginEmailField.setHintTextColor(Color.RED)
            } else if ((loginPasswordField.text.toString()).isEmpty()) {
                loginPasswordField.setHint("Please Enter your password")
                loginPasswordField.setHintTextColor(Color.RED)
            }

            val vFieldValidationError: String = fieldValidationError()
            if (vFieldValidationError != "No Error") {
                val builder = AlertDialog.Builder(requireContext())
                builder.setMessage("Please Complete All Required Fields")
                builder.setCancelable(true)
                builder.setNegativeButton("OK", DialogInterface.OnClickListener
                { dialog, which -> dialog.cancel() })
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(
                loginEmailField.text.toString(),
                loginPasswordField.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("AppDatabase", "AAA LogicAct 1")
                        signInButton.findNavController().navigate(R.id.action_loginFragment_to_containerFragment)

                        Log.d("AppDatabase", "AAA LogicAct 2")
                    } else {
                        val builder = AlertDialog.Builder(requireContext())
                        builder.setMessage("Please enter valid email address and password")
                        builder.setCancelable(true)
                        builder.setNegativeButton("OK", DialogInterface.OnClickListener
                        { dialog, which -> dialog.cancel() })
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.show()
                        Toast.makeText(requireContext(), "Login failed, please try again!", Toast.LENGTH_SHORT)
                    }
                }

        }
        // cancel_btn.setOnClickListener {
        // startActivity(Intent(this, LandingPage::class.java))
    }

    private fun fieldValidationError(): String {
        when (true) {
            (loginEmailField.text.toString()).isEmpty() -> {
                return "Please Enter your Email Address"

            }
            (loginPasswordField.text.toString()).isEmpty() -> {
                return "Please Enter Password"

            }
            else -> {
                return "No Error"
            }
        }
    }

}