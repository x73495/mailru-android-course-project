package com.airdron.mailcourse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var bottomNavigation: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        findViewById<TextView>(R.id.hello_world_text_view).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("", "")
            }

            startActivity(intent)
        }

        initViews()
        setupHandlers()
    }

    private fun initViews() {
        bottomNavigation = findViewById(R.id.bottom_navigation)
    }

    private fun setupHandlers() {
        setupBottomNavigationHandler()
    }

    private fun setupBottomNavigationHandler() {
        bottomNavigation?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile_page -> {
                    Log.d("test", "test profile")
                    true
                }
                R.id.schedule_page -> {
                    Log.d("test", "test schedule")
                    true
                }
                else -> false
            }
        }
    }
}
