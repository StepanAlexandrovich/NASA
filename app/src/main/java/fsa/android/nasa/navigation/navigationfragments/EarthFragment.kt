package fsa.android.nasa.navigation.navigationfragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fsa.android.nasa.databinding.FragmentEarthBinding

class EarthFragment: Fragment(), Names {
    private var _binding: FragmentEarthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getName(): String {
        return "EARTH"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageEarth.setOnTouchListener { view, event ->
            if(event.action==MotionEvent.ACTION_DOWN){
                animator(+675.0F,0.5F,2000)
            }else if(event.action==MotionEvent.ACTION_UP){
                animator(-675.0F,1.0F,2000)
            }
            true
        }
    }

    private fun animator(rotation:Float,scale:Float,duration:Long){
        ObjectAnimator.ofFloat(binding.moon,View.ROTATION,rotation).setDuration(duration).start()
        ObjectAnimator.ofFloat(binding.imageEarth,View.SCALE_X,scale).setDuration(1000).start()
        ObjectAnimator.ofFloat(binding.imageEarth,View.SCALE_Y,scale).setDuration(1000).start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}