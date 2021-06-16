package vn.tapbi.photo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.tapbi.photo.data.model.DataImage
import vn.tapbi.photo.databinding.ItemDataImageBinding
import vn.tapbi.photo.ui.custom.GlideImage
import java.io.File

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.HolderImage> {
    private var inter: IAdapterImage? = null

    constructor(inter: IAdapterImage?) : super() {
        this.inter = inter
    }

    class HolderImage(var binding: ItemDataImageBinding,inter: IAdapterImage?) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener {
                inter!!.getOnClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderImage {
          return HolderImage(ItemDataImageBinding.inflate(LayoutInflater.from(parent.context),parent,false),inter)
    }

    override fun onBindViewHolder(holder: HolderImage, position: Int) {
          holder.binding.data=inter!!.getData(position)


    }

    override fun getItemCount(): Int =inter!!.getSize()

    interface IAdapterImage {
        fun getSize(): Int
        fun getData(position: Int): DataImage
        fun getOnClick(position: Int)
    }
}