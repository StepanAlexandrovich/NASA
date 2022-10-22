package fsa.android.nasa.screenmain.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fsa.android.nasa.R
import fsa.android.nasa.databinding.BottomNavigationLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import fsa.android.nasa.simplefragments.CoordinatorFragment
import fsa.android.nasa.navigation.ViewPagerFragment
import fsa.android.nasa.navigation.navigationfragments.EarthFragment
import fsa.android.nasa.recycler.RecyclerFragment
import fsa.android.nasa.simplefragments.ConstraintFragment
import fsa.android.nasa.simplefragments.MotionFragment

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
                R.id.navigation_book -> {
                    navigateTo(ViewPagerFragment())
                    this.dismiss()
                }

                R.id.navigation_constraint_education -> {
                    navigateTo(ConstraintFragment())
                    this.dismiss()
                }

                R.id.navigation_coordinator_education -> {
                    navigateTo(CoordinatorFragment())
                    this.dismiss()
                }

                R.id.navigation_motion_education -> {
                    navigateTo(MotionFragment())
                    this.dismiss()
                }

                R.id.navigation_recycler_education -> {
                    navigateTo(RecyclerFragment())
                    this.dismiss()
                }
            }
            true
        }
    }

    private fun navigateTo(fragment: Fragment){
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container,fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}