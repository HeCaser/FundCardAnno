package com.example.fundcardanno

import android.app.Application
import com.example.commonui.FundCard_AutoGen

/**
 * @author: hepan
 * @date: 2022/4/26
 * @desc:
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        FundCard_AutoGen.init()
    }
}