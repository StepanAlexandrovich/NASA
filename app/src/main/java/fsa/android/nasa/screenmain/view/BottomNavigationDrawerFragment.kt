package fsa.android.nasa.screenmain.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import fsa.android.nasa.R
import fsa.android.nasa.databinding.BottomNavigationLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import fsa.android.nasa.navigation.OneTwoTreeFragment
import fsa.android.nasa.navigation.ViewPagerFragment

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> { requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ViewPagerFragment())
                        .addToBackStack(null)
                        .commit()
                }

                R.id.navigation_two -> { requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, OneTwoTreeFragment())
                    .addToBackStack(null)
                    .commit()
                }
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}