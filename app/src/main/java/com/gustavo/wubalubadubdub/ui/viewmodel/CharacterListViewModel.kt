package com.gustavo.wubalubadubdub.ui.viewmodel

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.gustavo.wubalubadubdub.base.BaseViewModel
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.source.repository.CharacterRepository
import com.gustavo.wubalubadubdub.source.repository.CharacterRepositoryImpl
import com.gustavo.wubalubadubdub.ui.CharacterListAdapter
import com.gustavo.wubalubadubdub.utils.extensions.isNotNullOrNotEmptyOrNotBlank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val repository: CharacterRepositoryImpl ) : BaseViewModel() {

    val mAdapter = CharacterListAdapter(::onCharacterClick)

    private lateinit var _charactersFlow: Flow<PagingData<Characters>>
    val charactersFlow: Flow<PagingData<Characters>>
        get() = _charactersFlow


    fun onCharacterClick(characters: Characters){
        Timber.tag("testeee").e(characters.name)
    }

    fun getCharacterList() {
        viewModelScope.launch {
            try {
                val result =   repository.getCharacters().cachedIn(viewModelScope)
                _charactersFlow = result
            } catch (ex: Exception) {
                errorMessage.value = ex.message
            }
        }
    }

}