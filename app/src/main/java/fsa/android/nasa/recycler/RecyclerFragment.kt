package fsa.android.nasa.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import fsa.android.nasa.databinding.FragmentRecyclerBinding
import fsa.android.nasa.recycler.interfaces.ItemEarthRealization
import fsa.android.nasa.recycler.interfaces.ItemHeaderRealization
import fsa.android.nasa.recycler.interfaces.ItemMarsRealization
import fsa.android.nasa.recycler.interfaces.ItemTouchHelperAdapter

class RecyclerFragment: Fragment() {
    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: RecyclerAdapter

    private val data = arrayListOf(
        Pair(Data(type = TYPE_HEADER, "Header"),false),
    )

    private val itemTouchHelperAdapter = object: ItemTouchHelperAdapter {
        override fun onItemMove(fromPosition: Int, toPosition: Int) {
            data.removeAt(fromPosition).apply {
                data.add(if (toPosition > fromPosition) toPosition - 1 else toPosition,
                    this)
            }
            adapter.setListData(data)
            adapter.notifyItemMoved(fromPosition, toPosition)
        }
        override fun onItemDismiss(position: Int) {
            data.removeAt(position)
            adapter.setListData(data)
            adapter.notifyItemRemoved(position)
        }
    }

    private val headerRealization = object: ItemHeaderRealization {
        override fun createMars(position: Int) {
            data.add(position,Pair(Data(type = TYPE_MARS,"Mars"),false))
            adapter.setListDataAdd(data,position)
        }
        override fun createEarth(position: Int) {
            data.add(position,Pair(Data(type = TYPE_EARTH,"Earth"),false))
            adapter.setListDataAdd(data,position)
        }
    }

    private val earthRealization = object: ItemEarthRealization {
        override fun move(position: Int, direction: Int) {
            if(border(position + direction)){
                data.removeAt(position).apply {
                    data.add(position + direction,this)
                }
                adapter.setListDataMove(data,position,direction)
            }
        }
    }

    private val marsRealization = object: ItemMarsRealization {
        override fun add(position: Int) {
            data.add(position,Pair(Data(type = TYPE_MARS,"Mars*"),false))
            adapter.setListDataAdd(data,position)
        }

        override fun remove(position: Int) {
            data.removeAt(position)
            adapter.setListDataRemove(data,position)
        }

        override fun move(position: Int, direction: Int) {
            if(border(position + direction)){
                data.removeAt(position).apply {
                    data.add(position + direction,this)
                }
                adapter.setListDataMove(data,position,direction)
            }
        }

        override fun switchVisible(position: Int) {
            data[position] = data[position].let {
                it.first to !it.second
            }
            adapter.setListData(data)
            adapter.notifyItemChanged(position)
        }
    }

    private fun border(position:Int):Boolean{
        if( position >=1 && position < data.size ) return true
        return false
    }



    //////////////////////////////////////////////////////////////////////////////

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapter(
            data,itemTouchHelperAdapter,
            headerRealization,earthRealization,marsRealization
        )
        binding.recyclerView.adapter = adapter

        ItemTouchHelper(ItemTouchHelperCallback(adapter))
            .attachToRecyclerView(binding.recyclerView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}