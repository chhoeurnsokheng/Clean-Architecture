/*
 * SkeletonViewLceLoadingView.kt
 * UGamer
 *
 * Created by Dell on 10/17/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce.view.impl.loading

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.ethanhua.skeleton.ViewSkeletonScreen
import com.example.myapplication.R
import com.icekh.ugamer.shared.lce.view.LceLoadingView
import com.icekh.ugamer.shared.skeleton.StatefulViewSkeletonScreen

class SkeletonViewLceLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LceLoadingView(context, attrs, defStyleAttr) {

    private var skeleton: StatefulViewSkeletonScreen? = null
    private val placeholderView: View

    init {
        View.inflate(context, R.layout.view_skeleton_view_lce_loading, this)
        placeholderView = findViewById(R.id.placeholderView)
    }

    fun setSkeletonBuilder(builder: (View) -> ViewSkeletonScreen.Builder) {
        val skeletonBuilder = builder(placeholderView)
        skeleton = StatefulViewSkeletonScreen(skeletonBuilder)
    }

    override fun show(isTranslucent: Boolean) {
        skeleton?.show()
    }

    override fun hide() {
        skeleton?.hide()
    }
}
