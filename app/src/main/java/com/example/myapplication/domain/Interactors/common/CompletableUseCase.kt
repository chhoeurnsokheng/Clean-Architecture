/*
 * CompletableUseCase.kt
 * UGamer
 *
 * Created by Dell on 11/25/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.domain.Interactors.common

import com.icekh.ugamer.utils.schedulers.SchedulersProvider
import io.reactivex.rxjava3.core.Completable

abstract class CompletableUseCase<in Param>(
    private val schedulersProvider: SchedulersProvider
) : UseCase() {

    abstract fun buildUseCaseCompletable(param: Param): Completable

    fun execute(
        onComplete: (() -> Unit) = {},
        onError: ((t: Throwable) -> Unit) = {},
        param: Param
    ) {
        checkIsAttachedToLifecycle()
        disposeLatest()
        val disposable = buildUseCaseCompletable(param)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.main())
            .subscribe(onComplete, onError)
        disposables.add(disposable)
        lastDisposable = disposable
    }
}
