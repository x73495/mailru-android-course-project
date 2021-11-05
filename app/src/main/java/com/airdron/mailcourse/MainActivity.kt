package com.airdron.mailcourse

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
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
    }
}
