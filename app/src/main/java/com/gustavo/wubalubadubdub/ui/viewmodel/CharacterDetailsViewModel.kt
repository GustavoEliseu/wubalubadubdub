package com.gustavo.wubalubadubdub.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gustavo.wubalubadubdub.base.BaseViewModel
import com.gustavo.wubalubadubdub.model.Characters
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(): BaseViewModel() {

    var characterDetails: Characters? = null

//Old Unused mutables, not worth it using it on a view that has static value
//  val mutableImageUrl = MutableLiveData<String>()
//  val mutableCharacterName = MutableLiveData<String>()
//  mutableImageUrl.value = characterDetails?.image
//  mutableCharacterName.value = characterDetails?.name

    fun initCharacterDetails(characters: Characters?){
        if(characters!= null && characters.isEmpty().not()){
            characterDetails = characters
        }else{
            //TODO
        }
    }
}