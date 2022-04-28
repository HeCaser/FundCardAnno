package com.example.fundcardanno

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.module1.ModuleOneActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.tvActivity1).setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity2::class.java))
        }
        findViewById<View>(R.id.tvActivity2).setOnClickListener {
            startActivity(Intent(this@MainActivity, ModuleOneActivity::class.java))
        }
    }
}




