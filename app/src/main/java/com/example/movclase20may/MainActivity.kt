package com.example.movclase20may

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var menu: TabLayout
    private lateinit var visor: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        menu = findViewById(R.id.tabMenu)
        visor = findViewById(R.id.vpVisor)

        val viewPagerAdapter = ControladorMenu(this)
        visor.adapter = viewPagerAdapter

        TabLayoutMediator(menu, visor){ tab, position ->
            tab.text = when(position){
                0 -> "Principal"
                1 -> "Galeria"
                2 -> "Contactanos"
                else -> null
            }
            tab.icon = when(position){
                0 -> getDrawable(R.drawable.baseline_add_home_24)
                1 -> getDrawable(R.drawable.baseline_bluetooth_searching_24)
                2 -> getDrawable(R.drawable.baseline_attach_email_24)
                else -> null
            }
        }.attach()
    }
}