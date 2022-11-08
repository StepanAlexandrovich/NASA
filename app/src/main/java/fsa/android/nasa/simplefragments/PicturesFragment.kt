package fsa.android.nasa.simplefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fsa.android.nasa.R
import fsa.android.nasa.databinding.FragmentPicturesBinding

class PicturesFragment:Fragment() {
    private var _binding: FragmentPicturesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPicturesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.bottom_view_today).isEnabled = true  // -> НЕ РАБОТАЕТ, НЕ ПОЛУЧАЕТСЯ ВЫДЕЛИТЬ НУЖНЫЙ ПУНКТ BUUTON VIEW на старте ????????????????????
        view.findViewById<View>(R.id.bottom_view_today).performClick()      // не нашёл другого рабочего способа ????????????????????????????????

        binding.bottomPictureOfTheDay.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.bottom_view_today -> {
                    binding.imageDay.setImageResource(R.drawable.days_today)
                    true
                }
                R.id.bottom_view_yesterday -> {
                    binding.imageDay.setImageResource(R.drawable.days_yesterday)
                    true
                }
                R.id.bottom_view_before_yesterday -> {
                    binding.imageDay.setImageResource(R.drawable.days_before_yesterday)
                    true
                }
                else -> false
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}