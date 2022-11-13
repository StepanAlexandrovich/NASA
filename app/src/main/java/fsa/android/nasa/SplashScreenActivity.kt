package fsa.android.nasa

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import fsa.android.nasa.databinding.ActivitySplashBinding
import fsa.android.nasa.launch.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySplashBinding

    private val rotateValue = 200f
    private val interpolatorDuration = 100L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MainActivity::class.java)

        binding.imageSplashScreen.animate().rotationBy(rotateValue)
            .setInterpolator(LinearInterpolator()).setDuration(interpolatorDuration)
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {
                    // Nothing to do
                }

                override fun onAnimationEnd(p0: Animator?) {
                    startActivity(intent)
                    finish()
                }

                override fun onAnimationCancel(p0: Animator?) {
                    // Nothing to do
                }

                override fun onAnimationRepeat(p0: Animator?) {
                    // Nothing to do
                }

            })
    }
}