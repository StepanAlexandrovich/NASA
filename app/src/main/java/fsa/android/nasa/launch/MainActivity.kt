package fsa.android.nasa.launch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fsa.android.nasa.R
import fsa.android.nasa.databinding.ActivityMainBinding
import fsa.android.nasa.screenmain.view.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
}