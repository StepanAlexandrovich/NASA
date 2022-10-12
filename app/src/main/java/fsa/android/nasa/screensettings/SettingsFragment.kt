package fsa.android.nasa.screensettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import fsa.android.nasa.R
import fsa.android.nasa.databinding.FragmentSettingsBinding
import fsa.android.nasa.launch.MyApp
import fsa.android.nasa.screenchips.ChipsFragment
import fsa.android.nasa.util.setThemeNasa

class SettingsFragment:Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setThemeLocal(MyApp.getSavedTheme().read())

        binding.chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                setThemeLocal(it.text as String)
            }
        }


    }

    private fun setThemeLocal(stringThemeOut:String){
        MyApp.getSavedTheme().write(stringThemeOut)
        setThemeNasa(stringThemeOut,requireActivity() as AppCompatActivity)
        createChipsFragment()
    }

    private fun createChipsFragment(){
        activity?.supportFragmentManager?.beginTransaction()?.
        add(R.id.containerSettings,ChipsFragment.newInstance())?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}