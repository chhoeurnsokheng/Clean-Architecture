/*
 * BaseLceViewModel.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.icekh.ugamer.data.entity.response.ErrorResponse
import com.icekh.ugamer.domain.common.UseCase
import com.icekh.ugamer.shared.ErrorType
import com.icekh.ugamer.shared.LiveDataState
import com.icekh.ugamer.shared.MutableLiveDataState
import com.icekh.ugamer.shared.onEmpty
import com.icekh.ugamer.shared.onError
import com.icekh.ugamer.shared.onLoading
import com.icekh.ugamer.shared.onSuccess
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection.HTTP_NOT_FOUND

abstract class BaseLceViewModel<T>(vararg useCases: UseCase) : ViewModel() {

    private val _dataState: MutableLiveDataState<T> = MutableLiveData()
    val dataState: LiveDataState<T> get() = _dataState

    private var useCaseList: MutableList<UseCase> = mutableListOf()

    init {
        useCaseList.addAll(
            useCases.map {
                it.attachToLifecycle()
                it
            }
        )
    }

    protected fun showLoading(isTranslucent: Boolean = false) {
        _dataState.onLoading(isTranslucent)
    }

    protected fun showContent(data: T) {
        _dataState.onSuccess(data)
    }

    protected fun showEmpty() {
        _dataState.onEmpty()
    }

    protected fun showError(throwable: Throwable) {
        _dataState.onError(mapErrorType(throwable))
    }

    protected fun showError(errorType: ErrorType) {
        _dataState.onError(errorType)
    }

    protected fun mapErrorType(throwable: Throwable): ErrorType {
        throwable.printStackTrace()
        return when (throwable) {
            is IOException -> {
                if (throwable is ConnectException) {
                    ErrorType.Critical(ErrorResponse(throwable = throwable))
                } else {
                    ErrorType.Network
                }
            }
            is HttpException -> {
                try {
                    when (throwable.code()) {
                        HTTP_NOT_FOUND -> {
                            ErrorType.NotFound
                        }
                        else -> {
                            val errorResponse = Gson().fromJson(
                                throwable.response()?.errorBody()?.string(),
                                ErrorResponse::class.java
                            )
                            errorResponse.throwable = throwable
                            ErrorType.Critical(errorResponse)
                        }
                    }
                } catch (ex: Exception) {
                    ErrorType.Critical(ErrorResponse(throwable = throwable))
                }
            }
            else -> ErrorType.Critical(ErrorResponse(throwable = throwable))
        }
    }

    override fun onCleared() {
        super.onCleared()
        useCaseList.forEach { it.dispose() }
    }
}
