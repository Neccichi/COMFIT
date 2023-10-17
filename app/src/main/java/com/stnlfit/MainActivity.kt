package com.stnlfit

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.stnlfit.fragment_main.HomeFragment
import com.stnlfit.fragment_profile.ProfileFragment
import com.stnlfit.fragment_second.SecondFragment
import com.stnlfit.fragment_third.ThirdFragment
import meow.bottomnavigation.MeowBottomNavigation

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: AHBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        // Создание элементов
        val item1 = AHBottomNavigationItem("Home", R.drawable.ic_main)
        val item2 = AHBottomNavigationItem("Second", R.drawable.ic_main)
        val item3 = AHBottomNavigationItem("Third", R.drawable.ic_main)
        val item4 = AHBottomNavigationItem("Profile", R.drawable.ic_profile)

        // Добавление элементов
        bottomNavigationView.addItem(item1)
        bottomNavigationView.addItem(item2)
        bottomNavigationView.addItem(item3)
        bottomNavigationView.addItem(item4)

        // Установим выбранным первый фрагмент
        bottomNavigationView.currentItem = 0

        // Загрузка начального фрагмента
        replaceFragment(HomeFragment())

        bottomNavigationView.setOnTabSelectedListener { position, wasSelected ->
            val selectedFragment: Fragment = when (position) {
                0 -> HomeFragment()
                1 -> SecondFragment()
                2 -> ThirdFragment()
                3 -> ProfileFragment()
                else -> HomeFragment()
            }
            replaceFragment(selectedFragment)
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}
