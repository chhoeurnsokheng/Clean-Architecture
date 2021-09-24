/*
 * SimpleLceErrorView.kt
 * UGamer
 *
 * Created by Dell on 10/17/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce.view.impl.error

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.example.myapplication.R
import com.example.myapplication.shared.ErrorType
import com.example.myapplication.utils.extension.gone
import com.example.myapplication.utils.extension.visible
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.icekh.ugamer.shared.lce.view.LceErrorView
import com.icekh.ugamer.shared.lce.view.LceState
import com.icekh.ugamer.shared.lce.view.LceState.ErrorState

class SimpleLceErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LceErrorView(context, attrs, defStyleAttr) {

    var errorTarget: String? = null
    private val retryButton: MaterialButton
    private val errorImageView: AppCompatImageView
    private val errorTitleTextView: MaterialTextView
    private val errorDescriptionTextView: MaterialTextView

    init {
        View.inflate(context, R.layout.view_lce_error, this)
        retryButton = findViewById(R.id.retryButton)
        errorImageView = findViewById(R.id.errorImageView)
        errorTitleTextView = findViewById(R.id.errorTitleTextView)
        errorDescriptionTextView = findViewById(R.id.errorDescriptionTextView)
    }

    override fun changeState(state: LceState) {
        super.changeState(state)
        if (state is ErrorState) {
            configureErrorView(state.errorType)
            VISIBLE
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun configureErrorView(error: ErrorType) {
        when (error) {
            is ErrorType.Network -> {
                errorImageView.setImageResource(R.drawable.ic_no_internet_connection)
                errorTitleTextView.setText(R.string.no_internet)
                errorDescriptionTextView.setText(R.string.no_internet_subtitle)
                retryButton.visible()
            }
            is ErrorType.NotFound -> {
                errorImageView.setImageResource(R.drawable.ic_empty)
                errorTitleTextView.setText(R.string.not_found)
                errorDescriptionTextView.text = if (errorTarget != null) {
                    context.getString(R.string.not_found_placeholder, errorTarget)
                } else {
                    context.getString(R.string.not_found)
                }
                retryButton.gone()
            }
            is ErrorType.Critical -> {
                errorImageView.setImageResource(R.drawable.ic_warning)
                errorTitleTextView.setText(R.string.something_went_wrong)
                errorDescriptionTextView.setText(R.string.something_went_wrong_subtitle)
                retryButton.visible()
            }
        }
    }

    fun setOnRetryClickListener(listener: OnClickListener) {
        retryButton.setOnClickListener(listener)
    }
}
