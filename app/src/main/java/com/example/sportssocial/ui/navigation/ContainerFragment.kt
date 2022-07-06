package com.example.sportssocial.ui.navigation

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.sportssocial.R
import com.example.sportssocial.util.Constants.Companion.AUTH
import com.google.android.gms.auth.api.Auth
import kotlinx.android.synthetic.main.fragment_container.*
import kotlinx.android.synthetic.main.fragment_container.view.*

class ContainerFragment : Fragment() {


    private val homeFragment = HomepageFragment()
    private val faveFragment = FavoritesFragment()
    private val highFragment = HighlightsFragment()
    private val proFragment = ProfileFragment()
    private val searFragment = SearchFragment()
    private val logFragment = LoginFragment()
    private lateinit var logoutButton : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_container, container, false)

        //Allows the user to log out
        logoutButton = view.findViewById(R.id.containerLogout)
        logoutButton.setOnClickListener {
            logoutAlert(it)
        }

        //Sets the Default Fragment on Launch
        replaceFragment(homeFragment)
        view.bottomNav.selectedItemId = R.id.homepageFragment
        view.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.homepageFragment -> replaceFragment(homeFragment)
                R.id.searchFragment -> replaceFragment(searFragment)
                R.id.favoritesFragment -> replaceFragment(faveFragment)
                R.id.highlightsFragment -> replaceFragment(highFragment)
                R.id.profileFragment -> replaceFragment(proFragment)
            }
            true
        }

        return view
    }

    //Call the parentFragmentManager, so a transaction can occur
    private fun replaceFragment(fragment : Fragment) {

        val transaction = parentFragmentManager
        transaction.beginTransaction().replace(
            R.id.fragmentContainer,
            fragment
            ).commit()
    }


    private fun logoutAlert(it : View) {

        AlertDialog.Builder(requireContext())
            .setMessage("Do you want to logout?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener {
                        dialog, logout ->
                    AUTH.signOut()
                    it.findNavController().navigate(R.id.action_containerFragment_to_loginFragment)
                    Toast.makeText(requireContext(), "You Are Now Logged Out.", Toast.LENGTH_LONG).show()
                    //TODO: LOGOUT METHOD AND NAVIGATION
                })
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener {
                        dialog, it -> dialog.cancel()
                })
            .create()
            .show()
    }
}