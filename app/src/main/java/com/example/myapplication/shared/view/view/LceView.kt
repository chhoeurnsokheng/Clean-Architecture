/*
 * LceView.kt
 * UGamer
 *
 * Created by Sopheak Te on 09/07/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce.view

import com.example.myapplication.shared.view.view.LceState

interface LceView {
    fun changeState(state: LceState)
}
