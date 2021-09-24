/*
 * LceState.kt
 * UGamer
 *
 * Created by Sopheak Te on 09/07/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce.view

import com.icekh.ugamer.shared.ErrorType

sealed class LceState {
    class LoadingState(val isTranslucent: Boolean = false) : LceState()

    object ContentState : LceState()

    object EmptyState : LceState()

    class ErrorState(val errorType: ErrorType) : LceState()
}
