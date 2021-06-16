package vn.tapbi.photo.ui.main

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import vn.tapbi.photo.data.model.DataImage
import vn.tapbi.photo.data.model.DataPath
import vn.tapbi.photo.databinding.FragmentChooseImageBinding
import vn.tapbi.photo.ui.adapter.ChooseImageAdapter
import vn.tapbi.photo.ui.base.BaseFragment
import java.io.File

class ChooseImageFragment : BaseFragment(), ChooseImageAdapter.IChooseImage {
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
        setFragmentResultListener("key_path_Folder") { requestKey, bundle ->
            var s = bundle.getString("data_path_Folder")
            loadPath(s!!)
        }
        setFragmentResultListener("key_name_Folder") { requestKey, bundle ->
            var name_folder = bundle.getString("data_name_Folder")
            binding!!.txtFolder.setText(name_folder)
        }
        return binding!!.root
    }


    @SuppressLint("UseRequireInsteadOfGet")
    private fun getImage(path: String) {
        //  var url = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        //var url=MediaStore.Files.getContentUri(path)
        //  var ur=Uri()
        var url2 = MediaStore.Images.Media.getContentUri(path)
        var cursor = context!!.contentResolver.query(
            url2,
            null,
            null,
            null,
            MediaStore.Images.Media.DATE_ADDED + " DESC"
        )
        // var arr = mutableListOf<DataPath>()
        cursor!!.moveToFirst()
        if (cursor != null) {
            while (!cursor.isAfterLast) {
                var path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                //   var file_name = File(File(path).parent).name
                arr_Choose.add(DataPath(path))
                // arr2.add(Datap(path))
                //  Log.d("parent: ", file_name)
                Log.d("path: ", path)
                Log.d("enter: ", "==================")
                cursor.moveToNext()
            }
            cursor.close()
        }
        // return arr
    }

    private fun loadPath(path: String) {
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

    override fun getSize(): Int = arr_Choose.size

    override fun getData(position: Int): DataPath = arr_Choose[position]

    override fun getOnClick(position: Int) {

    }
}