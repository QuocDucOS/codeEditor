package vn.tapbi.photo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import vn.tapbi.photo.R
import vn.tapbi.photo.ui.base.BaseAcivity
import vn.tapbi.photo.ui.base.BaseFragment

class MainActivity : BaseAcivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       openFragmentMain()
    }

    fun openFragmentAlbums() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fr, AlbumsFragment(), AlbumsFragment::javaClass.name)
            .hide(checkVisibleFragment()!!)
            .addToBackStack(null)
            .commit()
    }
    fun openFragmentImage() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fr, ChooseImageFragment(), ChooseImageFragment::javaClass.name)
            .hide(checkVisibleFragment()!!)
            .addToBackStack(null)
            .commit()
    }
    fun openFragmentMain(){
        supportFragmentManager.beginTransaction()
            .add(R.id.fr, SpalshFragment(), SpalshFragment::javaClass.name)
            .commit()
    }
    private fun checkVisibleFragment():Fragment?{
        for(fr in supportFragmentManager.fragments){
            if(fr.isVisible&&fr!=null){
                return fr
            }
        }
        return null
    }

    override fun onBackPressed() {
        var fr=checkVisibleFragment()
        if(fr!=null){
            (fr as BaseFragment).BackFragment()
        }
    }
}