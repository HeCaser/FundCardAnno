package com.example.module1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.commonui.CardFactory

class ModuleOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = CardFactory.getFundSubView(this, "2")

        if (view == null) {
            setContentView(R.layout.activity_module_one)
        }else{
            setContentView(view)
        }
    }
}