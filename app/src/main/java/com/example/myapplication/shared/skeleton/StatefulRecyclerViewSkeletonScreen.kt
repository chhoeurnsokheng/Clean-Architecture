/*
 * StatefulRecyclerViewSkeletonScreen.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/31/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.skeleton

import com.ethanhua.skeleton.RecyclerViewSkeletonScreen
import com.ethanhua.skeleton.SkeletonScreen

class StatefulRecyclerViewSkeletonScreen(
    private val builder: RecyclerViewSkeletonScreen.Builder
) : SkeletonScreen {

    private var skeletonScreen: RecyclerViewSkeletonScreen? = null

    var isVisible: Boolean = false

    override fun show() {
        if (!isVisible) {
            if (skeletonScreen == null) {
                skeletonScreen = builder.show()
            } else {
                skeletonScreen?.show()
            }
            isVisible = true
        }
    }

    override fun hide() {
        if (isVisible) {
            skeletonScreen?.hide()
            isVisible = false
        }
    }
}
