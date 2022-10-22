package fsa.android.nasa.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fsa.android.nasa.databinding.FragmentRecyclerBinding
import fsa.android.nasa.util.toast

class RecyclerFragment: Fragment() {
    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    private val data = arrayListOf(
        Pair(Data(type = TYPE_HEADER, "Header"),false),
        Pair(Data(type = TYPE_EARTH, "Earth"),false),
        Pair(Data(type = TYPE_EARTH, "Earth"),false),
        Pair(Data(type = TYPE_EARTH, "Earth"),false),
        Pair(Data(type = TYPE_EARTH, "Earth"),false),
        Pair(Data(type = TYPE_EARTH, "Earth"),false),
        Pair(Data(type = TYPE_MARS, "Mars"),false),
        Pair(Data(type = TYPE_MARS, "Mars"),false),
        Pair(Data(type = TYPE_MARS, "Mars"),false),
        Pair(Data(type = TYPE_MARS, "Mars"),false),
        Pair(Data(type = TYPE_MARS, "Mars"),false)
    )

    private val callbackAdd = AddItem {
        data.add(it,Pair(Data(type = TYPE_MARS,"Mars*"),false))
        adapter.setListDataAdd(data,it)
    }
    private val callbackRemove = RemoveItem {
        data.removeAt(it)
        adapter.setListDataRemove(data,it)
    }
    private fun border(position:Int):Boolean{
        if( position >=1 && position < data.size ) return true
        return false
    }
    private val callbackMoveUp = MoveUpItem {
        if(border(it-1)){
            data.removeAt(it).apply {
                data.add(it-1,this)
            }
            adapter.setListDataMove(data,it,-1)
        }
    }
    private val callbackMoveDown = MoveDownItem {
        if(border(it+1)){
            data.removeAt(it).apply {
                data.add(it+1,this)
            }
            adapter.setListDataMove(data,it,+1)
        }
    }

    private val callbackSwitchVisibleItem = SwitchVisibleItem { position ->
        data[position] = data[position].let {
            it.first to !it.second
        }
        adapter.setListData(data)
        adapter.notifyItemChanged(position)
    }

    lateinit var adapter: RecyclerAdapter

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
            data,
            callbackAdd,
            callbackRemove,
            callbackMoveUp,
            callbackMoveDown,
            callbackSwitchVisibleItem
        )
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}