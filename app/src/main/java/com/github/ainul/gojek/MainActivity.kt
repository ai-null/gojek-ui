package com.github.ainul.gojek

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.github.ainul.gojek.databinding.ActivityMainBinding
import com.github.ainul.gojek.IntroSliderAdapter.Companion.Intro
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val data: List<Intro> = listOf(
            Intro(
                "Selamat datang di Gojek!",
                "Aplikasi yang bikin hidup kamu lebih nyaman. Siap bantuin semua kehidupanmu, kapan pun dan di mana pun.",
                R.drawable.greetings
            ),
            Intro(
                "Transportasi & logistik",
                "Anterin kamu jalan atau ambilin barang lebih gampang tinggal ngeklik doang~",
                R.drawable.transport_and_logistics
            ),
            Intro(
                "Pesan makanan & belanja",
                "Lagi ngidam sesuatu? Gojek beliin gak pake lama.",
                R.drawable.order_food
            ),
            Intro(
                "Pembayaran",
                "Bisa beli pulsa, bayar tagihan listrik atau air, dan juga transfer sana sini.",
                R.drawable.payment
            )
        )

        binding.optionLang.setOnClickListener {
            // Snackbar.make(binding.optionLang, "Option clicked!", Snackbar.LENGTH_SHORT).show()
            showLanguageOptions()
        }

        val adapter = IntroSliderAdapter(data, this)
        binding.introSlider.adapter = adapter
        setupIndicator(data.size)
    }

    private fun showLanguageOptions() {
        val dialog = BottomSheetDialog(this, R.style.Theme_MaterialComponents_BottomSheetDialog)

        dialog.setContentView(R.layout.lang_option_bottom_sheet)

        dialog.window?.run {
            setBackgroundDrawable(ColorDrawable(0))
            dialog.show()
        }
    }

    private lateinit var indicators: ArrayList<ImageView>

    private fun setupIndicator(data: Int) {
        // define indicators container
        indicators = arrayListOf()

        // define layout used for indicator's View
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        ).also { it.setMargins(8, 0, 8, 0) }

        for (i in 0 until data) {
            val dot = ImageView(this)
            val drawable = ResourcesCompat.getDrawable(
                resources,
                if (i == 0) R.drawable.slider_indicator_active else R.drawable.slider_indicator_inactive,
                null
            )
            dot.setImageDrawable(drawable)

            // add view to the xml linear layout
            binding.sliderIndicator.addView(dot, layoutParams)

            // add view to the arrayList. Need to update the list on page change
            indicators.add(dot)
        }

        binding.introSlider.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                setCurrentIndicator(position)
            }

        })
    }

    private fun setCurrentIndicator(position: Int) {
        for (i in 0 until indicators.size) {
            val res =
                if (i == position) R.drawable.slider_indicator_active else R.drawable.slider_indicator_inactive
            val drawable = ResourcesCompat.getDrawable(resources, res, null)

            indicators[i].setImageDrawable(drawable)
        }
    }
}