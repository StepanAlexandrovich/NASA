package fsa.android.nasa.simplefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fsa.android.nasa.databinding.FragmentConstraintBinding

class ConstraintFragment:Fragment() {
    private var _binding: FragmentConstraintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }

    var switchVisible = true;
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button7.setOnClickListener(View.OnClickListener {
            switchVisible = !switchVisible
            if(switchVisible){
                binding.group1.visibility = View.VISIBLE
            }else{
                binding.group1.visibility = View.GONE
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}