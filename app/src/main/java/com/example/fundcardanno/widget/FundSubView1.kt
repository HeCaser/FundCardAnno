package com.example.fundcardanno.widget

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.apt_annotation.FundSubView

/**
 * @author: hepan
 * @date: 2022/4/11
 * @desc: 基金货卡子 View
 */

@FundSubView(type = "1")
class FundSubView1 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

    init {
        text = " I am FundSubView1 defined in app"
    }
}