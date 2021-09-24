package com.icekh.ugamer.shared.lce.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.icekh.ugamer.R

@Suppress("LeakingThis")
abstract class LceLoadingView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    LceView {

    companion object {
        private const val DEFAULT_START_TIME = -1L
        private const val MIN_SHOW_TIME = 0 // 500L
    }

    private var startTime = DEFAULT_START_TIME
    private var postedHide = false
    private var dismissed = false

    private val delayedHide = Runnable {
        postedHide = false
        startTime = DEFAULT_START_TIME
        visibility = GONE
        hide()
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        setBackgroundResource(R.color.colorBackground)
        isClickable = true
    }

    public override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        removeCallbacks()
    }

    public override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeCallbacks()
    }

    private fun removeCallbacks() {
        removeCallbacks(delayedHide)
    }

    override fun changeState(state: LceState) {
        post {
            if (state is LceState.LoadingState) {
                startTime = System.currentTimeMillis()
                dismissed = false
                postedHide = false
                visibility = VISIBLE
                show(state.isTranslucent)
                removeCallbacks(delayedHide)
            } else {
                dismissed = true
                val diff = System.currentTimeMillis() - startTime
                if (diff >= MIN_SHOW_TIME || startTime == DEFAULT_START_TIME) {
                    visibility = GONE
                    hide()
                } else {
                    if (!postedHide) {
                        postDelayed(delayedHide, MIN_SHOW_TIME - diff)
                        postedHide = true
                    }
                }
            }
        }
    }

    protected open fun show(isTranslucent: Boolean) {}

    protected open fun hide() {}
}
