package fsa.android.nasa.navigation.navigationfragments

import android.graphics.Rect
import android.os.Bundle
import android.transition.*
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fsa.android.nasa.R
import fsa.android.nasa.databinding.FragmentExplosionBinding

class ExplosionFragment: Fragment(), Names {
    private var _binding: FragmentExplosionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExplosionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = Adapter()
    }

    override fun getName(): String {
        return "EXPLOSION"
    }

    private inner class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animations_explode_list_item,
                    parent,
                    false
                ) as View
            )
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnTouchListener { view, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    explode(view)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    binding.recyclerView.adapter = Adapter()
                }
                true
            }
        }
        override fun getItemCount(): Int {
            return 32
        }

        private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    }

    private fun explode(it: View) {
        val rect = Rect()
        it.getGlobalVisibleRect(rect)

        val explode = Explode()
        explode.duration = 2000L

        explode.epicenterCallback = object:Transition.EpicenterCallback(){
            override fun onGetEpicenter(p0: Transition?): Rect {
                return rect
            }
        }

        TransitionManager.beginDelayedTransition(binding.explosion,explode)
        binding.recyclerView.adapter = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}