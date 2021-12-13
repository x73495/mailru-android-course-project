package company.vk.education.androidcourse.rememberthepills

import android.app.*
import android.content.BroadcastReceiver
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.room.Room
import company.vk.education.androidcourse.rememberthepills.room.RTPDatabase

import android.content.Context

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import company.vk.education.androidcourse.rememberthepills.notifications.NotificationsReceiver


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var db: RTPDatabase
    lateinit var notificationsReceiver: NotificationsReceiver
    // TODO should this be here..?
    var listOfNotificationIDs: MutableList<Int> = mutableListOf()

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDB()
        initNotifications()
        initViews()
    }

    private fun initViews() {
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val navView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        navView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navView.menu)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun initDB() {
        db = Room.databaseBuilder(
            applicationContext,
            RTPDatabase::class.java, "rtp-database"
        ).build()
    }

    private fun initNotifications() {
        val name = "Приём таблеток"
        val descriptionText = "Уведомления о приёмах таблеток"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("lol", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        notificationsReceiver = NotificationsReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        }
        registerReceiver(notificationsReceiver, filter)
    }
}