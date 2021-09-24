/*
 * SimpleLceLoadingView.kt
 * UGamer
 *
 * Created by Dell on 10/17/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce.view.impl.loading

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.example.myapplication.R
import com.icekh.ugamer.shared.lce.view.LceLoadingView

class SimpleLceLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LceLoadingView(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_lce_loading, this)
    }

    override fun show(isTranslucent: Boolean) {
        val background = if (isTranslucent) {
            R.color.black
        } else {
            R.color.teal_200
        }
        setBackgroundResource(background)
    }
}
