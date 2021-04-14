package com.github.ainul.gojek

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.viewpager.widget.PagerAdapter
import com.github.ainul.gojek.databinding.IntroSliderItemBinding

class IntroSliderAdapter(private val data: List<Intro>, private val context: Context) :
    PagerAdapter() {

    override fun getCount() = data.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val e = data[position]

        // define inflater and inflate it to the binding
        val inflater = LayoutInflater.from(context)
        val binding: IntroSliderItemBinding = IntroSliderItemBinding.inflate(inflater)

        // set data to the view
        binding.punchline.text = e.title
        binding.description.text = e.description
        e.imgSource?.let { binding.imgIntro.setImageResource(it) }

        // add view to the slider
        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    companion object {
        data class Intro(val title: String, val description: String, @DrawableRes val imgSource: Int? = R.drawable.sample_background1)
    }
}