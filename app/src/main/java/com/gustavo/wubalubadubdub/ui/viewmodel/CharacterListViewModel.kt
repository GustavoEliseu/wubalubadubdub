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
import com.gustavo.wubalubadubdub.ui.fragments.characters.CharacterListActions
import com.gustavo.wubalubadubdub.utils.extensions.isNotNullOrNotEmptyOrNotBlank
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val repository: CharacterRepositoryImpl ) : BaseViewModel() {

    val mAdapter = CharacterListAdapter(::onCharacterClick)
    val charactersFlow: Flow<PagingData<Characters>>
        get() = _charactersFlow
    private lateinit var _charactersFlow: Flow<PagingData<Characters>>
    private var characterListActions:CharacterListActions? = null

    fun initCharacterListActions(mCharacterListActions:CharacterListActions){
        characterListActions = mCharacterListActions
    }

    fun updateSearchTerm(term:String?){
        repository.updatePagingDatSource(term)
    }

    fun getCharacterList() {
        viewModelScope.launch {
            try {
                val result =   repository.getCharacters().cachedIn(viewModelScope).cancellable()
                _charactersFlow = result
            } catch (ex: Exception) {
                errorMessage.value = ex.message
            }
        }
    }

    //Private functions
    private fun onCharacterClick(characters: Characters?){
        characterListActions?.onCharacterClick(characters)
    }
}