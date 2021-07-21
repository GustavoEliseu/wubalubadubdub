package com.gustavo.wubalubadubdub.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gustavo.wubalubadubdub.R
import com.gustavo.wubalubadubdub.base.BaseActivity
import com.gustavo.wubalubadubdub.databinding.ActivityCharacterDetailsBinding
import com.gustavo.wubalubadubdub.model.Characters
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterDetailsActions
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterDetailsViewModel
import com.gustavo.wubalubadubdub.ui.viewmodel.CharacterDetailsViewModelFactory
import com.gustavo.wubalubadubdub.utils.CHARACTER

fun Context.characterDetailsIntent(characters: Characters): Intent {
    return Intent(this, CharacterDetailsActivity::class.java).apply {
            putExtra(CHARACTER, characters)
    }
}
class CharacterDetailsActivity:
    BaseActivity<CharacterDetailsViewModel, CharacterDetailsViewModelFactory>(), CharacterDetailsActions {

    private val characters: Characters? by lazy {
        intent.extras?.getSerializable(CHARACTER) as? Characters
    }

    private lateinit var mBinding: ActivityCharacterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, getLayoutId())
        initCharacter()
        mViewModel.characterActions = this
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.executePendingBindings()
        initializeUi()
    }

    override fun getLayoutId(): Int = R.layout.activity_character_details

    override fun getViewModelClass(): Class<CharacterDetailsViewModel> = CharacterDetailsViewModel::class.java

    override fun initializeUi() {
        if(characters?.id !=null){
            mViewModel.initCharacterDetails(characters)
        }
    }

    override fun close() {
        super.close()
        onBackPressed()
    }

    private fun initCharacter(){
        val firstEpisodeNumber = characters?.episode?.first()?.split("/")?.last()
        val lastEpisodeNumber = characters?.episode?.last()?.split("/")?.last()
        characters?.firstEpisode = getString(R.string.episode_number,firstEpisodeNumber )
        characters?.lastEpisode = getString(R.string.episode_number,lastEpisodeNumber )
        characters?.imageDescription = getString(R.string.image_description,characters?.name)
        mViewModel.initCharacterDetails(characters)
    }

}