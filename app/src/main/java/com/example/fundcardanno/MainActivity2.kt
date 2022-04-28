package com.example.fundcardanno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.commonui.CardFactory

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = CardFactory.getFundSubView(this, "1")
        if (view == null) {
            setContentView(R.layout.activity_main2)
        }else{
            setContentView(view)
        }
    }
}