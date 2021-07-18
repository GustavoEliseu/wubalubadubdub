package com.gustavo.wubalubadubdub.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CharacterDetailsViewModelFactory @Inject constructor(
    private val characterDetailsViewModel: CharacterDetailsViewModel
): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
            return characterDetailsViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}