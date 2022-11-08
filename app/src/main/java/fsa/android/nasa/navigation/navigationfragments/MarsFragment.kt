package fsa.android.nasa.navigation.navigationfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import fsa.android.nasa.R
import fsa.android.nasa.databinding.FragmentMarsBinding

class MarsFragment: Fragment(), Names {

    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    private var process = false;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getName(): String {
        return "MARS"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var anim = AnimationUtils.loadAnimation(requireContext(),R.anim.rotating_element)

        binding.imageMars.setOnClickListener{
            process = !process
            if(process){
                binding.imageMars.startAnimation(anim)
            }else{
                binding.imageMars.clearAnimation()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}