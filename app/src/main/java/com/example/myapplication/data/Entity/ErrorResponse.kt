/*
 * ErrorResponse.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.data.Entity

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("statusCode") var statusCode: Int? = null,
    @SerializedName("message") var message: String? = null,
    @SerializedName("error") var error: String? = null,
    @SerializedName("errorCode") var errorCode: Int? = null,
    var throwable: Throwable? = null
)
