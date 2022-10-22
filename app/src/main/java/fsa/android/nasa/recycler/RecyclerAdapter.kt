package fsa.android.nasa.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fsa.android.nasa.databinding.ItemEarthBinding
import fsa.android.nasa.databinding.ItemHeaderBinding
import fsa.android.nasa.databinding.ItemMarsBinding

class RecyclerAdapter(
    private var listData: List<Data>
) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            TYPE_EARTH->{
                val binding = ItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(binding)
            }
            TYPE_MARS->{
                val binding = ItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(binding)
            }
            else -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    abstract class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Data)
    }

    inner class EarthViewHolder(val binding: ItemEarthBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Data){
            binding.itemName.text = data.name
        }
    }

    inner class MarsViewHolder(val binding: ItemMarsBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Data){
            binding.itemName.text = data.name
        }
    }

    inner class HeaderViewHolder(val binding: ItemHeaderBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Data){
            binding.itemName.text = data.name
        }
    }

}