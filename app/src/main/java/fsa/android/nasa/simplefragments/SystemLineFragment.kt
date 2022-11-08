package fsa.android.nasa.simplefragments

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import fsa.android.nasa.databinding.FragmentSystemLineBinding

class SystemLineFragment: Fragment(),View.OnClickListener {
    private var _binding: FragmentSystemLineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSystemLineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.systemCanvas.setOnClickListener(this)
        binding.buttonPlusPoint.setOnClickListener(this)
        binding.buttonMinusPoint.setOnClickListener(this)
        binding.chipGroupModeSystem.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                when(it.text){
                    "Black hole" -> {
                        binding.systemCanvas.modeHole()
                        binding.systemCanvas.restart()
                    }
                    "Ufo"        -> {
                        binding.systemCanvas.modeUfo()
                        binding.systemCanvas.restart()
                    }
                    "Heart"      -> {
                        binding.systemCanvas.modeHeart()
                        binding.systemCanvas.restart()
                    }
                }
            }
        }

        val text = "\ntouch the screen to start the animation\ntouch the screen again to stop the animation"
        val spannableString = SpannableString(text)

        val result = text.indexesOf("\n")
        var current = result.first()
        result.forEach{
            if(current!=it){
                spannableString.setSpan(
                    BulletSpan(20,Color.RED),
                    current+1,it,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            current = it
        }
        spannableString.setSpan(
            BulletSpan(20,Color.RED),
            current+1,text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.instruction.text = spannableString


    }

    private fun String.indexesOf(substr:String, ignoreCase: Boolean = true):List<Int> =
        ( if(ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr))
            .findAll(this).map { it.range.first }.toList()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            binding.systemCanvas.id ->{
                binding.systemCanvas.switchProcess()
            }
            binding.buttonPlusPoint.id ->{
                binding.systemCanvas.plusPoint()
            }
            binding.buttonMinusPoint.id ->{
                binding.systemCanvas.minusPoint()
            }
        }
    }

}