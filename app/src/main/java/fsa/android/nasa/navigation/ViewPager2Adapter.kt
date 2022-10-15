package fsa.android.nasa.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import fsa.android.nasa.navigation.navigationfragments.EarthFragment
import fsa.android.nasa.navigation.navigationfragments.MarsFragment
import fsa.android.nasa.navigation.navigationfragments.SystemFragment

class ViewPager2Adapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position] as Fragment
    }
}