package com.icekh.ugamer.shared.lce.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

abstract class LceErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    LceView {

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    override fun changeState(state: LceState) {
        visibility = if (state is LceState.ErrorState) VISIBLE else GONE
    }
}
