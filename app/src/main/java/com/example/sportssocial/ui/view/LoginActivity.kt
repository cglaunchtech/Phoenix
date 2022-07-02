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
import androidx.appcompat.widget.AppCompatButton
import com.example.sportssocial.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var signInButton: AppCompatButton
    lateinit var signUpButton: TextView
    lateinit var forgotLogin: TextView
    lateinit var loginEmailField: TextInputEditText
    lateinit var loginPasswordField: TextInputEditText
    lateinit var viewerButton: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        signInButton = findViewById(R.id.signInButton)
        signUpButton = findViewById(R.id.signUpButton)
        forgotLogin = findViewById(R.id.forgotLogin)
        loginEmailField = findViewById(R.id.loginEmailField1)
        loginPasswordField = findViewById(R.id.loginPasswordField1)
        viewerButton = findViewById(R.id.viewerButton)

        auth = FirebaseAuth.getInstance()

        val currentuser = auth.currentUser
        if (currentuser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        forgotLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (loginEmailField.text.toString().isNotEmpty()) {

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

                                Log.d(ContentValues.TAG, "Email sent.")
                            }
                            //startActivity(Intent(this@LoginActivity, LandingPage::class.java))

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
            val myIntent = Intent(this, SignUp::class.java)
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
            auth.signInWithEmailAndPassword(
                loginEmailField.text.toString(),
                loginPasswordField.text.toString()
            )
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Log.d("AppDatabase", "AAA LogicAct 1")
                        startActivity(Intent(this, MainActivity::class.java))
//
                        finish()

                        Log.d("AppDatabase", "AAA LogicAct 2")
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
       }
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