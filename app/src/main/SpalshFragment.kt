package vn.tapbi.photo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import vn.tapbi.photo.R
import vn.tapbi.photo.databinding.FragmentMainBinding
import vn.tapbi.photo.ui.base.BaseFragment

class SpalshFragment : BaseFragment(), View.OnClickListener {
    private var binding: FragmentMainBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding!!.btnChoose.setOnClickListener(this)
        return binding!!.root
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btn_choose->{
                (activity as MainActivity).openFragmentAlbums()
            }
        }
    }
}