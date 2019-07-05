package kz.zangpro.sportqaz

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx
import kz.zangpro.sportqaz.fragments.ListNewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottom_nav = findViewById<BottomNavigationViewEx>(R.id.bottom_nav_menu)
        bottom_nav.setTextVisibility(false)
        bottom_nav.enableItemShiftingMode(false)
        bottom_nav.enableShiftingMode(false)
        bottom_nav.enableAnimation(false)

        for (i in 0 until bottom_nav.menu.size()) {
            bottom_nav.setIconTintList(i, null)
        }

        supportFragmentManager.beginTransaction().replace(R.id.container, ListNewsFragment()).commit()


    }

}
