package com.example.sportssocial.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.sportssocial.R
import com.example.sportssocial.data.repo.FirestoreRepo
import com.example.sportssocial.ui.viewmodel.MainViewModel
import com.example.sportssocial.util.Constants.Companion.AUTH
import com.firebase.ui.auth.data.model.User.getUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    var databaseReference : DatabaseReference? = null
    var database = FirestoreRepo()
    lateinit var auth: FirebaseAuth
    lateinit var firstNameField : View
    lateinit var editButton : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        auth = FirebaseAuth.getInstance()
        val vm : MainViewModel by viewModels()


        editButton = view.findViewById(R.id.editButton)
        editButton.setOnClickListener {
            replaceFragment(EditProfileFragment())
        }

        return view
    }


    private fun replaceFragment(fragment : Fragment) {

        val transaction = parentFragmentManager
        transaction.beginTransaction().replace(
            R.id.fragmentContainer,
            fragment
        ).commit()
    }

}