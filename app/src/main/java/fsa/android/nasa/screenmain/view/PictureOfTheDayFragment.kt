package fsa.android.nasa.screenmain.view

import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import fsa.android.nasa.R

import fsa.android.nasa.databinding.FragmentPictureOfTheDayBinding
import fsa.android.nasa.launch.MainActivity
import fsa.android.nasa.launch.MyApp
import fsa.android.nasa.simplefragments.SettingsFragment
import fsa.android.nasa.screenmain.viewmodel.PictureOfTheDayData
import fsa.android.nasa.screenmain.viewmodel.PictureOfTheDayViewModel
import fsa.android.nasa.util.*

class PictureOfTheDayFragment:Fragment() {
    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var viewCopy: View? = null

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(PictureOfTheDayViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setThemeNasa(MyApp.getSavedTheme().read(),requireActivity() as AppCompatActivity)

        viewModel.getData().observe(viewLifecycleOwner) { renderData(it) }
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view.findViewById<View>(R.id.bottom_view_before_yesterday).isSelected = true  // не работает
        view.findViewById<View>(R.id.bottom_view_today).performClick()      // не нашёл другого рабочего способа
        view.findViewById<View>(R.id.bottom_view_today).isPressed = true

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))

        binding.bottomPictureOfTheDay.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.bottom_view_today -> {
                    viewModel.sendServerRequest(stringDateToday())
                    true
                }
                R.id.bottom_view_yesterday -> {
                    viewModel.sendServerRequest(stringDateYesterday())
                    true
                }
                R.id.bottom_view_before_yesterday -> {
                    viewModel.sendServerRequest(stringDateTheDayBeforeYesterday())
                    true
                }
                else -> false
            }
        }

        viewCopy = view

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
        setBottomAppBar(view)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.app_bar_fav ->   toast("Favorite")
            R.id.app_bar_settings -> activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, SettingsFragment.newInstance())
                ?.addToBackStack(null)
                ?.commit()
            android.R.id.home -> {
                activity?.let {
                    BottomNavigationDrawerFragment().show(it.supportFragmentManager, "tag")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData

                displayPictureOnImageView(serverResponseData.url,binding.imageView,requireContext())
                displayTextOnTextView(serverResponseData.title, viewCopy?.findViewById(R.id.bottomSheetDescriptionHeader) as TextView)
                displayTextOnTextView(serverResponseData.explanation, viewCopy?.findViewById(R.id.bottomSheetDescription) as TextView)
            }
            is PictureOfTheDayData.Loading -> {
                toast("downloading...")
            }
            is PictureOfTheDayData.Error -> {
                toast(data.error.message.toString())
            }
        }
    }

    private fun setBottomAppBar(view: View) {
        val context = activity as MainActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_back_fab))
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_plus_fab))
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }
}