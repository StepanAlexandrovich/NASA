package fsa.android.nasa.navigation.navigationfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fsa.android.nasa.R

class SystemFragment: Fragment(), Names {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_system,container,false)
    }

    override fun getName(): String {
        return "SOLAR SYSTEM"
    }
}