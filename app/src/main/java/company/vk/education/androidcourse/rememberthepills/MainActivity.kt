package company.vk.education.androidcourse.rememberthepills

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import company.vk.education.androidcourse.rememberthepills.components.base.utils.hideKeyboard

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        navView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navView.menu)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, _ , _ ->
            currentFocus?.clearFocus()
            navView.hideKeyboard()
        }
    }
}