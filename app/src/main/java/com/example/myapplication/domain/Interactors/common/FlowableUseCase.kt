/*
 * FlowableUseCase.kt
 * UGamer
 *
 * Created by Dell on 11/25/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.domain.Interactors.common

import com.icekh.ugamer.utils.schedulers.SchedulersProvider
import io.reactivex.rxjava3.core.Flowable

abstract class FlowableUseCase<T, in Param>(
    private val schedulersProvider: SchedulersProvider
) : UseCase() {

    abstract fun buildUseCaseFlowable(param: Param): Flowable<T>

    fun execute(
        onNext: ((t: T) -> Unit) = {},
        onError: ((t: Throwable) -> Unit) = {},
        onComplete: (() -> Unit) = {},
        param: Param
    ) {
        checkIsAttachedToLifecycle()
        disposeLatest()
        val disposable = buildUseCaseFlowable(param)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.main())
            .distinctUntilChanged()
            .onBackpressureLatest()
            .subscribe(onNext, onError, onComplete)
        disposables.add(disposable)
        lastDisposable = disposable
    }
}
