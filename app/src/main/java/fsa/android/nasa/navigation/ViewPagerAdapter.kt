package fsa.android.nasa.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import fsa.android.nasa.navigation.navigationfragments.EarthFragment
import fsa.android.nasa.navigation.navigationfragments.MarsFragment
import fsa.android.nasa.navigation.navigationfragments.Names
import fsa.android.nasa.navigation.navigationfragments.ExplosionFragment

class ViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    private val fragments = arrayOf(EarthFragment(), MarsFragment(), ExplosionFragment())

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position] as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return (fragments[position] as Names).getName()
    }
}