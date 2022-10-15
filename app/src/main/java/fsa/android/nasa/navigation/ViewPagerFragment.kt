package fsa.android.nasa.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import fsa.android.nasa.databinding.FragmentViewPagerBinding
import fsa.android.nasa.navigation.navigationfragments.Names

class ViewPagerFragment: Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPager2Adapter(requireActivity())
        binding.viewPager.adapter  = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager
        ) { tab, position -> tab.text = (adapter.fragments[position] as Names).getName() }.attach()
    }

}