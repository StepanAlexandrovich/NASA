package fsa.android.nasa.screenmain.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fsa.android.nasa.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import fsa.android.nasa.animation.system.core.RotationRelativelyZeroComposition
import fsa.android.nasa.databinding.BottomNavigationFavoritesBinding
import fsa.android.nasa.navigation.ViewPagerFragment
import fsa.android.nasa.simplefragments.*

class FavoritesDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationFavoritesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomNavigationFavoritesBinding.inflate(inflater, container, false)
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
                R.id.navigation_text -> {
                    RotationRelativelyZeroComposition.modeDegree()
                    navigateTo(TextFragment())
                    this.dismiss()
                }
                R.id.navigation_system -> {
                    RotationRelativelyZeroComposition.modeInversion()
                    navigateTo(SystemLineFragment())
                    this.dismiss()
                }
                R.id.navigation_tree -> {
                    RotationRelativelyZeroComposition.modeInversion()
                    navigateTo(SystemTreeFragment())
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