/*
 * SingleUseCase.kt
 * UGamer
 *
 * Created by Dell on 11/25/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.domain.Interactors.common

import com.icekh.ugamer.utils.schedulers.SchedulersProvider
import io.reactivex.rxjava3.core.Single

abstract class SingleUseCase<T, in Param>(
    private val schedulersProvider: SchedulersProvider
) : UseCase() {

    abstract fun buildUseCaseSingle(param: Param): Single<T>

    fun execute(
        onSuccess: ((T) -> Unit) = {},
        onError: ((Throwable) -> Unit) = {},
        param: Param
    ) {
        checkIsAttachedToLifecycle()
        disposeLatest()
        val disposable = buildUseCaseSingle(param)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.main())
            .subscribe(onSuccess, onError)
        disposables.add(disposable)
        lastDisposable = disposable
    }
}
