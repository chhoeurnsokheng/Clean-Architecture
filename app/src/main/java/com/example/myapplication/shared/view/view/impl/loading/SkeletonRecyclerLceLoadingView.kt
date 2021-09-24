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
import androidx.recyclerview.widget.RecyclerView
import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.example.myapplication.R
import com.icekh.ugamer.shared.lce.view.LceLoadingView
import com.icekh.ugamer.shared.skeleton.StatefulRecyclerViewSkeletonScreen

class SkeletonRecyclerLceLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LceLoadingView(context, attrs, defStyleAttr) {

    private var skeleton: StatefulRecyclerViewSkeletonScreen? = null
    private val recyclerView: RecyclerView

    init {
        View.inflate(context, R.layout.view_skeleton_recycler_lce_loading, this)
        recyclerView = findViewById(R.id.recyclerView)
    }

    fun setSkeletonBuilder(builder: (RecyclerView) -> RecyclerViewSkeletonScreen.Builder) {
        val skeletonBuilder = builder(recyclerView)
        skeleton = StatefulRecyclerViewSkeletonScreen(skeletonBuilder)
    }

    override fun show(isTranslucent: Boolean) {
        skeleton?.show()
    }

    override fun hide() {
        skeleton?.hide()
    }
}
