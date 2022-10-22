package fsa.android.nasa.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fsa.android.nasa.databinding.FragmentRecyclerBinding

class RecyclerFragment: Fragment() {
    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arrayListOf(
            Data(type = TYPE_HEADER, "Header"),
            Data(type = TYPE_EARTH, "Earth"),
            Data(type = TYPE_EARTH, "Earth"),
            Data(type = TYPE_EARTH, "Earth"),
            Data(type = TYPE_EARTH, "Earth"),
            Data(type = TYPE_EARTH, "Earth"),
            Data(type = TYPE_EARTH, "Earth"),
            Data(type = TYPE_EARTH, "Earth"),
            Data(type = TYPE_EARTH, "Earth"),
            Data(type = TYPE_MARS, "Mars"),
            Data(type = TYPE_MARS, "Mars"),
            Data(type = TYPE_MARS, "Mars"),
            Data(type = TYPE_MARS, "Mars"),
            Data(type = TYPE_MARS, "Mars"),
            Data(type = TYPE_MARS, "Mars"),
        )

        binding.recyclerView.adapter = RecyclerAdapter(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}