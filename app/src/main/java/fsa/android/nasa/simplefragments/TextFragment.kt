package fsa.android.nasa.simplefragments

import android.graphics.Color
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.DynamicDrawableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import fsa.android.nasa.R
import fsa.android.nasa.animation.text.TextAnimationGlobe
import fsa.android.nasa.databinding.FragmentTextBinding
import fsa.android.nasa.util.coloredText
import java.util.*

class TextFragment: Fragment() {
    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!

    private lateinit var textAnim:TextAnimationGlobe

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewI.text = coloredText(SpannableString("I"),Color.MAGENTA)

        textAnim = TextAnimationGlobe(binding.textViewCoding,"CODING",requireActivity())
        textAnim.start()

        binding.floatingActionButton.setOnClickListener{
            binding.heartEarthCanvas.switchProcess()
            textAnim.switchProcess()

            if(binding.heartEarthCanvas.getProcess()){
                binding.floatingActionButton.setImageResource(R.drawable.stop)
            }else{
                binding.floatingActionButton.setImageResource(R.drawable.play)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        textAnim.stop()

        //binding.heartEarthCanvas -> нужно ли у него останавливать invalidate или вообще как то его уничтожать ?????????
    }

}