/*
 * ObservableUseCase.kt
 * UGamer
 *
 * Created by Dell on 11/25/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.domain.Interactors.common

import com.icekh.ugamer.utils.schedulers.SchedulersProvider
import io.reactivex.rxjava3.core.Observable

abstract class ObservableUseCase<T, in Param>(
    private val schedulersProvider: SchedulersProvider
) : UseCase() {

    abstract fun buildUseCaseObservable(param: Param): Observable<T>

    fun execute(
        onSuccess: ((t: T) -> Unit) = {},
        onError: ((t: Throwable) -> Unit) = {},
        onComplete: (() -> Unit) = {},
        param: Param
    ) {
        checkIsAttachedToLifecycle()
        disposeLatest()
        val disposable = buildUseCaseObservable(param)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.main())
            .subscribe(onSuccess, onError, onComplete)
        disposables.add(disposable)
        lastDisposable = disposable
    }
}
