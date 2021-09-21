/*
 * TextViewExtension.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.utils.extension

import android.os.Build
import android.text.Html
import android.widget.TextView
import com.example.myapplication.R

@Suppress("DEPRECATION")
fun TextView.loadHtml(htmlData: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlData, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(htmlData)
    }
}

fun TextView.loadHtmlNotAvailableIfNull(htmlData: String?) {
    this.loadHtml(htmlData ?: context.getString(R.string.not_available))
}

fun TextView.setTextNotAvailableIfNull(src: String?) {
    text = if (src?.trim().isNullOrEmpty()) context.getString(R.string.not_available) else src
}

fun TextView.setTextAsYesOrNo(state: Boolean?) {
    text = context.getString(if (state == true) R.string.yes else R.string.no)
}
