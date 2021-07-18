package com.gustavo.wubalubadubdub.ui.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.gustavo.wubalubadubdub.R
import com.gustavo.wubalubadubdub.base.BaseActivity
import com.gustavo.wubalubadubdub.ui.fragments.characters.CharacterListFragment
import com.gustavo.wubalubadubdub.ui.viewmodel.MainViewModel
import com.gustavo.wubalubadubdub.ui.viewmodel.MainViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi



class MainActivity : BaseActivity<MainViewModel, MainViewModelFactory>() {

    private val charactersFragment =  CharacterListFragment()
//    val locationsFragment = LocationsFragment()
//    val episodesFragment = EpisodesFragment()
    val fm = supportFragmentManager
    var active: Fragment = charactersFragment
    private var bottomNav: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        fm.beginTransaction().add(R.id.main_container, episodesFragment, "3").hide(fragment3).commit();
//        fm.beginTransaction().add(R.id.main_container, locationsFragment, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container,charactersFragment, "1").show(charactersFragment).commit()

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_characters -> {
                    fm.beginTransaction().hide(active).show(charactersFragment).commit()
                    active = charactersFragment
                }
//                R.id.navigation_locations -> {
//                    fm.beginTransaction().hide(active).show(locationsFragment).commit()
//                    active = locationsFragment
//                }
//                R.id.navigation_episodes -> {
//                    fm.beginTransaction().hide(active).show(episodesFragment).commit()
//                    active = episodesFragment
//                }
            }
            false
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun initializeUi() {

    }
}