/*
 * SnackbarExtension.kt
 * UGamer
 *
 * Created by Sopheak Te on 09/29/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.utils.extension

import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

fun View.createSnackbar(
    message: String,
    duration: Int = Snackbar.LENGTH_LONG,
    anchorView: View? = null,
    startIconId: Int? = null
): Snackbar {
    val snackbar = Snackbar.make(this, message, duration)
    snackbar.anchorView = anchorView
    startIconId?.let { iconId ->
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            .let { textView ->
                textView.setCompoundDrawablesWithIntrinsicBounds(iconId, 0, 0, 0)
                textView.compoundDrawablePadding = context.smallSpace
            }
    }
    return snackbar
}
