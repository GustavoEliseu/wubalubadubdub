package com.gustavo.wubalubadubdub.ui.Activity

import android.content.Context
import android.content.Intent
import com.gustavo.wubalubadubdub.R
import com.gustavo.wubalubadubdub.base.BaseActivity
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterDetailsViewModel
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterDetailsViewModelFactory
import com.gustavo.wubalubadubdub.utils.CHARACTER

fun Context.characterDetailsIntent(characters: Characters): Intent {
    return Intent(this, CharacterDetailsActivity::class.java).apply {
            putExtra(CHARACTER, characters)
    }
}
class CharacterDetailsActivity:
    BaseActivity<CharacterDetailsViewModel, CharacterDetailsViewModelFactory>() {

    private val characters: Characters? by lazy {
        intent.extras?.getSerializable(CHARACTER) as? Characters
    }

    override fun getLayoutId(): Int = R.layout.activity_character_details

    override fun getViewModelClass(): Class<CharacterDetailsViewModel> = CharacterDetailsViewModel::class.java

    override fun initializeUi() {
    }

}