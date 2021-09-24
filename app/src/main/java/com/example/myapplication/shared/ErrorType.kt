/*
 * ErrorType.kt
 * UGamer
 *
 * Created by Dell on 11/11/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.shared

import com.example.myapplication.data.Entity.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection

sealed class ErrorType {
    object Network : ErrorType()

    object NotFound : ErrorType()

    class Critical(val error: ErrorResponse) : ErrorType()

    companion object {
        fun mapErrorType(throwable: Throwable): ErrorType {
            throwable.printStackTrace()
            return when (throwable) {
                is IOException -> {
                    if (throwable is ConnectException) {
                        Critical(ErrorResponse(throwable = throwable))
                    } else {
                        Network
                    }
                }
                is HttpException -> {
                    try {
                        when (throwable.code()) {
                            HttpURLConnection.HTTP_NOT_FOUND -> {
                                NotFound
                            }
                            else -> {
                                val errorResponse = Gson().fromJson(
                                    throwable.response()?.errorBody()?.string(),
                                    ErrorResponse::class.java
                                )
                                errorResponse.throwable = throwable
                                Critical(errorResponse)
                            }
                        }
                    } catch (ex: Exception) {
                        Critical(ErrorResponse(throwable = throwable))
                    }
                }
                else -> Critical(ErrorResponse(throwable = throwable))
            }
        }
    }
}
