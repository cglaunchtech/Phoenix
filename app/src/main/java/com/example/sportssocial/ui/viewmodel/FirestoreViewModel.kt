package com.example.sportssocial.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sportssocial.data.repo.FirestoreRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirestoreViewModel @Inject constructor(
    private val repo : FirestoreRepo) : ViewModel() {
}