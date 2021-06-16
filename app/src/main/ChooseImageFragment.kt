package vn.tapbi.photo.ui.main

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import vn.tapbi.photo.R
import vn.tapbi.photo.data.model.DataImage
import vn.tapbi.photo.data.model.DataPath
import vn.tapbi.photo.databinding.FragmentChooseImageBinding
import vn.tapbi.photo.ui.adapter.ChooseImageAdapter
import vn.tapbi.photo.ui.base.BaseFragment
import java.io.File

class ChooseImageFragment : BaseFragment(), ChooseImageAdapter.IChooseImage, View.OnClickListener {
    private var binding: FragmentChooseImageBinding? = null
    private var arr_Choose = mutableListOf<DataPath>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChooseImageBinding.inflate(inflater, container, false)
        binding!!.rcChoose.adapter = ChooseImageAdapter(this)
        binding!!.rcChoose.layoutManager = GridLayoutManager(context, 3)
        binding!!.back.setOnClickListener(this)
        setFragmentResultListener("key_path_Folder") { requestKey, bundle ->
            var s = bundle.getString("data_path_Folder")
            loadPath(s!!)
            //getImage("external")
        }
        setFragmentResultListener("key_name_Folder") { requestKey, bundle ->
            var name_folder = bundle.getString("data_name_Folder")
            binding!!.txtFolder.setText(name_folder)

        }
        return binding!!.root
    }


    @SuppressLint("UseRequireInsteadOfGet")
    private fun getImage(path: String) {
        var url2 = MediaStore.Images.Media.getContentUri(path)
        var cursor = context!!.contentResolver.query(
            url2,
            null,
            null,
            null,
            MediaStore.Images.Media.DATE_ADDED + " DESC"
        )
        cursor!!.moveToFirst()
        if (cursor != null) {
            while (!cursor.isAfterLast) {
                var path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                arr_Choose.add(DataPath(path))
                Log.d("path: ", path)
                Log.d("enter: ", "==================")
                cursor.moveToNext()
            }
            cursor.close()
        }
        // return arr
    }

    private fun loadPath(path: String) {
        if(path.equals("external")){
            getImage(path)
        }else{
            val f = File(path)
            val fs = f.listFiles()
            if (fs == null) {
                return
            }
            for (childPath in fs) {
                if (childPath.path.contains(".png") ||
                    childPath.path.contains(".jpg") ||
                    childPath.path.contains(".gif")
                    || childPath.path.contains(".jpeg")
                ) {
                    arr_Choose.add(
                        DataPath(
                            childPath.path,
                        )
                    )
                }
            }
        }

    }

    override fun getSize(): Int = arr_Choose.size

    override fun getData(position: Int): DataPath = arr_Choose[position]

    override fun getOnClick(position: Int) {
        Toast.makeText(context, arr_Choose[position].path_Folder, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.back -> {
                (activity as MainActivity).onBackPressed()
            }
        }
    }
}