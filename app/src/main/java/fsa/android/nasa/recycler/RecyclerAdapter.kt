package fsa.android.nasa.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fsa.android.nasa.databinding.ItemEarthBinding
import fsa.android.nasa.databinding.ItemHeaderBinding
import fsa.android.nasa.databinding.ItemMarsBinding

class RecyclerAdapter(
    private var listData: List<Pair<Data,Boolean>>,

    val callbackAdd:AddItem,
    val callbackRemove:RemoveItem,
    val callbackMoveUp:MoveUpItem,
    val callbackMoveDown:MoveDownItem,
    val callbackSwitchVisibleItem: SwitchVisibleItem
) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {

    fun setListDataAdd(listData: List<Pair<Data,Boolean>>, position: Int){
        this.listData = listData
        notifyItemInserted(position)
    }

    fun setListDataRemove(listData: List<Pair<Data,Boolean>>,position: Int){
        this.listData = listData
        notifyItemRemoved(position)
    }

    fun setListDataMove(listData: List<Pair<Data,Boolean>>,position: Int,direction:Int){
        this.listData = listData
        notifyItemMoved(position,position + direction)
    }

    fun setListData(listData: List<Pair<Data,Boolean>>){
        this.listData = listData
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].first.type
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
        abstract fun bind(data: Pair<Data,Boolean>)
    }

    inner class EarthViewHolder(val binding: ItemEarthBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>){
            binding.itemName.text = data.first.name
        }
    }

    inner class MarsViewHolder(val binding: ItemMarsBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>){
            binding.itemName.text = data.first.name
            binding.marsDescriptionTextView.visibility =
                if(listData[layoutPosition].second) View.VISIBLE
                else View.GONE

            binding.addItemImageView.setOnClickListener{
                callbackAdd.add(layoutPosition)
            }
            binding.removeItemImageView.setOnClickListener{
                callbackRemove.remove(layoutPosition)
            }
            binding.moveItemUp.setOnClickListener{
                callbackMoveUp.moveUp(layoutPosition)
            }
            binding.moveItemDown.setOnClickListener{
                callbackMoveDown.moveDown(layoutPosition)
            }
            binding.marsImageView.setOnClickListener{
                callbackSwitchVisibleItem.switchVisible(layoutPosition)
            }
        }
    }

    inner class HeaderViewHolder(val binding: ItemHeaderBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>){
            binding.itemName.text = data.first.name
        }
    }

}