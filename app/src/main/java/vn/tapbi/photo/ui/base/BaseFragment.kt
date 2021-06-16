package vn.tapbi.photo.ui.base

import androidx.fragment.app.Fragment

open class BaseFragment:Fragment() {
    fun BackFragment(){
        (activity as BaseAcivity).onBack()
    }

}