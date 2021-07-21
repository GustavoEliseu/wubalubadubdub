package com.gustavo.wubalubadubdub.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gustavo.wubalubadubdub.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(): BaseViewModel() {
    var mainActions: MainActions? = null
    var mutableTitle = MutableLiveData<String>()
    val titleVisibility = MutableLiveData<Int?>()
    val characterSearchTerm = MutableLiveData<String>()

    fun initMainActions(newMainActions:MainActions?){
        mainActions = newMainActions
    }
}