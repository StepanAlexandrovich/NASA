package fsa.android.nasa.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fsa.android.nasa.databinding.ItemEarthBinding
import fsa.android.nasa.databinding.ItemHeaderBinding
import fsa.android.nasa.databinding.ItemMarsBinding

class RecyclerAdapter(
    private var listData: List<Pair<Data,Boolean>>,
    private val itemTouchHelperAdapter: ItemTouchHelperAdapter,

    val callbackItemHeaderRealization: ItemHeaderRealization,
    val callbackItemEarthRealization: ItemEarthRealization,
    val callbackItemMarsRealization: ItemMarsRealization
) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(),ItemTouchHelperAdapter{

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

    abstract class BaseViewHolder(val view: View) : RecyclerView.ViewHolder(view),ItemTouchHelperViewHolder {
        abstract fun bind(data: Pair<Data,Boolean>)

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.CYAN)
        }
        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class EarthViewHolder(val binding: ItemEarthBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>){
            binding.itemName.text = data.first.name
            binding.moveItemUp.setOnClickListener{
                callbackItemEarthRealization.move(layoutPosition,-1)
            }
            binding.moveItemDown.setOnClickListener{
                callbackItemEarthRealization.move(layoutPosition,+1)
            }
        }
    }

    inner class MarsViewHolder(val binding: ItemMarsBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>){
            binding.itemName.text = data.first.name
            binding.marsDescriptionTextView.visibility =
                if(listData[layoutPosition].second) View.VISIBLE
                else View.GONE

            binding.addItemImageView.setOnClickListener{
                callbackItemMarsRealization.add(layoutPosition)
            }
            binding.removeItemImageView.setOnClickListener{
                callbackItemMarsRealization.remove(layoutPosition)
            }
            binding.moveItemUp.setOnClickListener{
                callbackItemMarsRealization.move(layoutPosition,-1)
            }
            binding.moveItemDown.setOnClickListener{
                callbackItemMarsRealization.move(layoutPosition,+1)
            }
            binding.marsImageView.setOnClickListener{
                callbackItemMarsRealization.switchVisible(layoutPosition)
            }
        }
    }

    inner class HeaderViewHolder(val binding: ItemHeaderBinding) : BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>){
            // TODO пока ничего не связывает

            binding.buttonCreateEarth.setOnClickListener {
                callbackItemHeaderRealization.createEarth(layoutPosition + 1)
            }
            binding.buttonCreateMars.setOnClickListener {
                callbackItemHeaderRealization.createMars(layoutPosition + 1)
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        itemTouchHelperAdapter.onItemMove(fromPosition,toPosition)
    }
    override fun onItemDismiss(position: Int) {
        itemTouchHelperAdapter.onItemDismiss(position)
    }


}