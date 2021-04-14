package com.github.ainul.gojek

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val slider: ViewPager = findViewById(R.id.login_slider)
        val sliderAdapter = LoginAdapter(listOf(0, 1, 2, 3, 4 ,5), this)


        slider.adapter = sliderAdapter
        slider.setPadding(100, 0, 100, 0)
    }
}