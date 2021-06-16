package vn.tapbi.photo.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import vn.tapbi.photo.data.model.DataImage
import vn.tapbi.photo.data.model.DataPath
import vn.tapbi.photo.databinding.FragmentChooseImageBinding
import vn.tapbi.photo.databinding.ItemDataChooseImageBinding

class ChooseImageAdapter : RecyclerView.Adapter<ChooseImageAdapter.HolderChoose> {
    private var inter: IChooseImage? = null

    constructor(inter: IChooseImage?) : super() {
        this.inter = inter
    }

    class HolderChoose(var binding: ItemDataChooseImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderChoose {
        return HolderChoose(
            ItemDataChooseImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HolderChoose, position: Int) {
        holder.binding.data = inter!!.getData(position)
    }

    override fun getItemCount(): Int = inter!!.getSize()

    interface IChooseImage {
        fun getSize(): Int
        fun getData(position: Int): DataPath
        fun getOnClick(position: Int)
    }
}