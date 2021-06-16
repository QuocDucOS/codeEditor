package vn.tapbi.photo.ui.base

import androidx.appcompat.app.AppCompatActivity

open class BaseAcivity:AppCompatActivity() {
    fun onBack(){
        super.onBackPressed()
    }
}