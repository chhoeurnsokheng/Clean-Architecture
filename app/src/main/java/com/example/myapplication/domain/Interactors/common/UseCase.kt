/*
 * UseCase.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.domain.Interactors.common

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class UseCase {

    private var isAttached = false
    protected var lastDisposable: Disposable? = null
    val disposables = CompositeDisposable()

    protected fun checkIsAttachedToLifecycle() {
        if (!isAttached) {
            throw RuntimeException("You have to attach ${this.javaClass.name} to the lifecycle.")
        }
    }

    fun attachToLifecycle() {
        isAttached = true
    }

    fun disposeLatest() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun dispose() {
        disposables.clear()
    }
}
