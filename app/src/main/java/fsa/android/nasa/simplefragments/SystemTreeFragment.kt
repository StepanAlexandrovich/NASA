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
import fsa.android.nasa.databinding.FragmentSystemTreeBinding

class SystemTreeFragment: Fragment(),View.OnClickListener {
    private var _binding: FragmentSystemTreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSystemTreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.treeCanvas.setOnClickListener(this)
        binding.buttonPlusPoint.setOnClickListener(this)
        binding.buttonMinusPoint.setOnClickListener(this)
        binding.chipGroupModeSystem.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                when(it.text){
                    "Empty" -> {
                        binding.treeCanvas.modeEmpty()
                        binding.treeCanvas.restart()
                    }
                    "Filled"        -> {
                        binding.treeCanvas.modeFilled()
                        binding.treeCanvas.restart()
                    }
                }
            }
        }

        binding.chipGroupTreeSystem.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                when(it.text){
                    "Tree*" -> {
                        binding.treeCanvas.treeOne()
                        binding.treeCanvas.restart()
                    }
                    "Tree**"        -> {
                        binding.treeCanvas.treeTwo()
                        binding.treeCanvas.restart()
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
            binding.treeCanvas.id ->{
                binding.treeCanvas.switchProcess()
            }
            binding.buttonPlusPoint.id ->{
                binding.treeCanvas.plusPoint()
            }
            binding.buttonMinusPoint.id ->{
                binding.treeCanvas.minusPoint()
            }
        }
    }

}