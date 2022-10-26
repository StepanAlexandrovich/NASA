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
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import fsa.android.nasa.R
import fsa.android.nasa.databinding.FragmentSystemBinding
import java.util.*

class SystemFragment: Fragment() {

    private var _binding: FragmentSystemBinding? = null
    private val binding get() = _binding!!

    private lateinit var timer:Timer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSystemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ANIMATION //
        binding.container1.rotation = 180.0F
        val system1 = System(binding.container1)
        val system2 = System(binding.container2)

        // TEXT //
        val textAnim = TextAnimation()

        // TIMER
        var process = false
        timer = Timer()
        timer.scheduleAtFixedRate(object :TimerTask() {
            override fun run() {
                if(process){
                    system1.process()
                    system2.process()

                    textAnim.process()
                }
            }
        },0,25)

        binding.floatingActionButton.setOnClickListener{
            process = !process
            if(process){
                binding.floatingActionButton.setImageResource(R.drawable.stop)
            }else{
                binding.floatingActionButton.setImageResource(R.drawable.play)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timer.cancel()
    }

    // animation
    inner class System(val view: View){
        var direction = 1
        fun process(){
            view.rotation += direction
        }
    }

    // text
    inner class TextAnimation(){
        private var step = 0
        private var rotation = 0
        private val text:String = "CODING"
        private val spannableString = SpannableString(text)

        init{
            greenText(spannableString)
            earthReplacesO(spannableString)

            binding.textViewI.text = greenText(SpannableString("I"))
            binding.textViewCoding.text = spannableString
        }

        fun process(){

            if(rotation%5==0){
                greenText(spannableString)
                spannableString.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.cherry)),
                    step,step+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE )
                step++
                if(step==text.length){
                    step = 0
                }
                binding.textViewCoding.text = spannableString
            }

            rotation++
            if(rotation == 360){
                rotation = 0
            }
        }

        private fun greenText(spannableString: SpannableString):SpannableString{
            spannableString.setSpan(
                ForegroundColorSpan(Color.GREEN),
                0, spannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannableString.setSpan(
                StyleSpan(BOLD),
                0, spannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            return spannableString
        }

        private fun earthReplacesO(spannableString: SpannableString):SpannableString{
            val verticalAlignment = DynamicDrawableSpan.ALIGN_BASELINE
            val bitmap = ContextCompat.getDrawable(requireContext(), R.drawable.globe_earth)!!.toBitmap()

            for(i in text.indices){
                if(text[i] == 'O'){
                    spannableString.setSpan(
                        ImageSpan(requireContext(),bitmap),
                        i,i+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE )
                }
            }

            return spannableString
        }

    }

}