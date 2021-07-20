package com.gustavo.wubalubadubdub.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.gustavo.wubalubadubdub.model.Characters

class CharacterItemViewModel: ViewModel() {

    var onClick: ((Characters?) ->Unit)? = null
    var characters: Characters? = null

    fun bind(mCharacters: Characters, onItemClick:(Characters?) -> Unit){
        onClick = onItemClick
        characters = mCharacters
    }
}