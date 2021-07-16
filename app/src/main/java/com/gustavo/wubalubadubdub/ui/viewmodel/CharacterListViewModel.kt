package com.gustavo.wubalubadubdub.ui.viewmodel

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import androidx.lifecycle.viewModelScope
import com.gustavo.wubalubadubdub.base.BaseViewModel
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.source.repository.CharacterRepository
import com.gustavo.wubalubadubdub.utils.extensions.isNotNullOrNotEmptyOrNotBlank
import io.reactivex.Flowable
import javax.inject.Inject

class CharacterListViewModel @Inject constructor(private val repository: CharacterRepository ) : BaseViewModel() {

    fun getCharacterList(): Flowable<PagingData<Characters>> {
        return repository
            .getCharacters()
            .map { pagingData -> pagingData.filter { it.image.isNotNullOrNotEmptyOrNotBlank() } }
            .cachedIn(viewModelScope)
    }

}