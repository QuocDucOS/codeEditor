package vn.tapbi.photo.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import vn.tapbi.photo.R
import vn.tapbi.photo.data.model.*
import vn.tapbi.photo.databinding.FragmentAlbumsBinding
import vn.tapbi.photo.ui.adapter.ImageAdapter
import vn.tapbi.photo.ui.base.BaseFragment
import vn.tapbi.photo.ui.custom.GlideImage
import java.io.File

class AlbumsFragment : BaseFragment(), ImageAdapter.IAdapterImage, View.OnClickListener {
    private var binding: FragmentAlbumsBinding? = null
    private var arr_image = mutableListOf<DataImage>()
    private var arr_path = mutableListOf<DataPathImage>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        binding!!.rc.adapter = ImageAdapter(this)
        binding!!.rc.layoutManager = GridLayoutManager(context, 2)
        binding!!.close.setOnClickListener(this)

        check()
        //   getFolderImage()
        return binding!!.root
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun getFolderImage(): MutableList<DataImage> {

        var url = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        arr_image.add(DataImage("external","Albums"))
        Log.d("url: ", url.toString())
        //  var ur=MediaStore.Files.getContentUri(path)
        var cursor = context!!.contentResolver.query(
            url,
            null,
            null,
            null,
            MediaStore.Images.Media.DATE_ADDED + " DESC"
        )
        var arr = mutableListOf<DataImage>()

        cursor!!.moveToFirst()
        if (cursor != null) {
            //content://media/external/images/media
            while (!cursor.isAfterLast) {
                var path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))

                var file_name = File(File(path).parent).name
                var path_folder = File(File(path).parent).path
                arr.add(DataImage(path_folder, file_name))
                arr_path.add(DataPathImage(path_folder))
                Log.d("parent: ", file_name)
                Log.d("path: ", path)
                Log.d("enter: ", "==================")
                cursor.moveToNext()
            }
            cursor.close()
        }
        return arr
    }

    fun check() {
        var arr1 = getFolderImage()
        for (arr in arr1) {
            if (!arr_image.contains(arr)) {
//                for (s in arr.name_Folder){
//
//                }
               // if (arr.name_Folder.)
                arr_image.add(arr)
            }
        }

    }

    override fun getSize(): Int = arr_image.size

    override fun getData(position: Int): DataImage = arr_image[position]

    override fun getOnClick(position: Int) {
        (activity as MainActivity).openFragmentImage()
        setFragmentResult(
            "key_path_Folder",
            bundleOf("data_path_Folder" to arr_image[position].path_Folder)
        )
        setFragmentResult(
            "key_name_Folder",
            bundleOf("data_name_Folder" to arr_image[position].name_Folder)
        )

    }

    override fun onClick(p0: View?) {
        when (p0!!.id) {
            R.id.close -> {
                (activity as MainActivity).onBackPressed()
            }
        }
    }
}