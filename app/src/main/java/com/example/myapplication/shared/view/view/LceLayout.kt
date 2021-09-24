/*
 * LceLayout.kt
 * UGamer
 *
 * Created by Sopheak Te on 09/07/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.icekh.ugamer.R
import com.icekh.ugamer.shared.lce.view.LceState.EmptyState
import com.icekh.ugamer.shared.lce.view.LceState.ErrorState
import com.icekh.ugamer.shared.lce.view.LceState.LoadingState

class LceLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    LceView {

    init {
        View.inflate(context, R.layout.view_lce_layout, this)
    }

    val contentView = findViewById<LceContentView>(R.id.contentView)
    private var loadingView: LceLoadingView? = null
    private var emptyView: LceEmptyView? = null
    private var errorView: LceErrorView? = null

    private val _loadingPlaceholderView = findViewById<View>(R.id.loadingPlaceholderView)
    private val _emptyPlaceholderView = findViewById<View>(R.id.emptyPlaceholderView)
    private val _errorPlaceholderView = findViewById<View>(R.id.errorPlaceholderView)

    fun setupLayout(
        @LayoutRes contentLayoutId: Int,
        loadingView: LceLoadingView? = null,
        emptyView: LceEmptyView? = null,
        errorView: LceErrorView? = null
    ) {
        setupContentView(contentLayoutId)
        this.loadingView = loadingView
        this.emptyView = emptyView
        this.errorView = errorView
    }

    private fun setupContentView(@LayoutRes contentLayoutId: Int) {
        contentView.removeAllViews()
        View.inflate(context, contentLayoutId, contentView)
    }

    override fun changeState(state: LceState) {
        when (state) {
            is LoadingState -> {
                replaceViewIfNeeded(_loadingPlaceholderView, loadingView)
            }
            is EmptyState -> {
                replaceViewIfNeeded(_emptyPlaceholderView, emptyView)
            }
            is ErrorState -> {
                replaceViewIfNeeded(_errorPlaceholderView, errorView)
            }
            else -> {
            }
        }

        loadingView?.changeState(state)
        emptyView?.changeState(state)
        errorView?.changeState(state)
        contentView.changeState(state)
    }

    private fun replaceViewIfNeeded(placeholderView: View, actualView: View?) {
        val index = this.indexOfChild(placeholderView)
        if (actualView == null || index == -1) return
        removeViewAt(index)
        addView(actualView, index)
    }
}
