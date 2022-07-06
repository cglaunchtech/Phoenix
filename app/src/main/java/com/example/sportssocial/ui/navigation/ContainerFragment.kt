package com.example.sportssocial.ui.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sportssocial.R
import kotlinx.android.synthetic.main.fragment_container.*
import kotlinx.android.synthetic.main.fragment_container.view.*

class ContainerFragment : Fragment() {


    private val homeFragment = HomepageFragment()
    private val faveFragment = FavoritesFragment()
    private val highFragment = HighlightsFragment()
    private val proFragment = ProfileFragment()
    private val searFragment = SearchFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_container, container, false)

        //Sets the Default Fragment on Launch
        replaceFragment(homeFragment)
        view.bottomNav.selectedItemId = R.id.homepageFragment
        view.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
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


    private fun replaceFragment(fragment : Fragment) {

        val transaction = parentFragmentManager
        transaction.beginTransaction().replace(
            R.id.fragmentContainer,
            fragment
            ).commit()
    }
}