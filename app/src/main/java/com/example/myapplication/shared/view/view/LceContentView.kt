/*
 * LceContentView.kt
 * UGamer
 *
 * Created by Sopheak Te on 09/07/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class LceContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    LceView {

    override fun changeState(state: LceState) {
        val isContentState = state is LceState.ContentState
        val isTranslucent = state is LceState.LoadingState && state.isTranslucent
        visibility = if (isContentState || isTranslucent) VISIBLE else GONE
    }
}
