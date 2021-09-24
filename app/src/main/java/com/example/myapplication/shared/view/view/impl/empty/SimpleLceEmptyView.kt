/*
 * SimpleLceEmptyView.kt
 * UGamer
 *
 * Created by Dell on 10/17/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce.view.impl.empty

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import com.example.myapplication.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.icekh.ugamer.shared.lce.view.LceEmptyView

class SimpleLceEmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LceEmptyView(context, attrs, defStyleAttr) {

    private val emptyButton: MaterialButton
    private val emptyImageView: AppCompatImageView
    private val emptyTitleTextView: MaterialTextView
    private val emptyDescriptionTextView: MaterialTextView

    init {
        View.inflate(context, R.layout.view_lce_empty, this)
        emptyButton = findViewById(R.id.emptyButton)
        emptyImageView = findViewById(R.id.emptyImageView)
        emptyTitleTextView = findViewById(R.id.emptyTitleTextView)
        emptyDescriptionTextView = findViewById(R.id.emptyDescriptionTextView)
    }

    fun setOnEmptyClickListener(listener: OnClickListener) {
        emptyButton.setOnClickListener(listener)
    }

    fun customiseEmptyImage(@DrawableRes image: Int) {
        emptyImageView.setImageResource(image)
    }

    fun customiseEmptyTitle(@StringRes message: Int) {
        emptyTitleTextView.setText(message)
    }

    fun customiseEmptyTitleVisibility(isVisible: Boolean = false) {
        emptyTitleTextView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun customiseEmptyDescription(@StringRes message: Int) {
        emptyDescriptionTextView.setText(message)
    }

    fun customiseEmptyDescriptionVisibility(isVisible: Boolean = false) {
        emptyDescriptionTextView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun customiseEmptyButtonVisibility(isVisible: Boolean = false) {
        emptyButton.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    fun customiseEmptyButtonText(@StringRes text: Int) {
        emptyButton.setText(text)
    }
}
