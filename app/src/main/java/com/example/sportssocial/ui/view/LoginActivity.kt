package com.example.sportssocial.ui.view

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sportssocial.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import timber.log.Timber

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var signInButton: Button
    lateinit var signUpButton: Button


    lateinit var forgotLogin: TextView
    lateinit var loginEmailField: TextInputEditText
    lateinit var loginPasswordField: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        try{
            signInButton = findViewById(R.id.signInButton)
            signUpButton = findViewById(R.id.signUpButton)
            forgotLogin = findViewById(R.id.forgotLogin)
            loginEmailField = findViewById(R.id.loginEmailField1)
            loginPasswordField = findViewById(R.id.loginPasswordField1)
        } catch (e: Exception){
            Timber.e(e)
        }

        auth = FirebaseAuth.getInstance()

        val currentuser = auth.currentUser
        if (currentuser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        forgotLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (loginEmailField.text.toString().isNotEmpty()) {

                    try{
                        auth.sendPasswordResetEmail(loginEmailField.text.toString())
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val builder = AlertDialog.Builder(this@LoginActivity)
                                    builder.setMessage("Email Sent to ${loginEmailField.text.toString()}")
                                    builder.setCancelable(true)
                                    builder.setNegativeButton("OK", DialogInterface.OnClickListener
                                    { dialog, which -> dialog.cancel() })
                                    val alertDialog: AlertDialog = builder.create()
                                    alertDialog.show()

                                    Timber.tag(ContentValues.TAG).d("Email sent.")
                                }
                                //startActivity(Intent(this@LoginActivity, LandingPage::class.java))

                            }
                    }catch (e:FirebaseAuthException){
                        Timber.e(e)
                    }

                } else {
                    val builder = AlertDialog.Builder(this@LoginActivity)
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
            val myIntent = Intent(this, RegistrationForm::class.java)
            startActivity(myIntent)
        }
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
                val builder = AlertDialog.Builder(this@LoginActivity)
                builder.setMessage("Please Complete All Required Fields")
                builder.setCancelable(true)
                builder.setNegativeButton("OK", DialogInterface.OnClickListener
                { dialog, which -> dialog.cancel() })
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
                return@setOnClickListener
            }
            try{
                auth.signInWithEmailAndPassword(
                    loginEmailField.text.toString(),
                    loginPasswordField.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Timber.tag("AppDatabase").d("AAA LogicAct 1")
                            startActivity(Intent(this, MainActivity::class.java))
//
                            finish()

                            Timber.tag("AppDatabase").d("AAA LogicAct 2")
                        } else {
                            val builder = AlertDialog.Builder(this@LoginActivity)
                            builder.setMessage("Please enter valid email address and password")
                            builder.setCancelable(true)
                            builder.setNegativeButton("OK", DialogInterface.OnClickListener
                            { dialog, which -> dialog.cancel() })
                            val alertDialog: AlertDialog = builder.create()
                            alertDialog.show()
                            Toast.makeText(this, "Login failed, please try again!", Toast.LENGTH_SHORT)
                        }
                    }
            } catch (e:FirebaseAuthException){
                Timber.e(e)
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
                return "Welcome!"
            }
        }
    }
}