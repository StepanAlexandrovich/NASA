package fsa.android.nasa.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import fsa.android.nasa.R
import fsa.android.nasa.databinding.FragmentOneTwoTreeBinding

class OneTwoTreeFragment:Fragment() {
    private var _binding: FragmentOneTwoTreeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOneTwoTreeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_one -> {
                    navigateTo(EarthFragment())
                    true
                }
                R.id.bottom_view_two -> {
                    navigateTo(MarsFragment())
                    true
                }
                R.id.bottom_view_tree -> {
                    navigateTo(SystemFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateTo(fragment: Fragment){
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.containerOneTwoTree,fragment).commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}