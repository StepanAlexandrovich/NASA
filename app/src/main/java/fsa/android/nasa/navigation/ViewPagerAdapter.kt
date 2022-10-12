package fsa.android.nasa.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
    private val fragments = arrayOf(EarthFragment(),MarsFragment(),SystemFragment())

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