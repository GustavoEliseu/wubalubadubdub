package com.gustavo.wubalubadubdub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CharacterListViewModelFactory @Inject constructor(
    private val characterListViewModel: CharacterListViewModel
): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterListViewModel::class.java)) {
            return characterListViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}