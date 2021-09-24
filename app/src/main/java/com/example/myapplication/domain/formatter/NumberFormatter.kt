/*
 * NumberFormatter.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.domain.formatter

import java.text.DecimalFormat
import java.util.Currency

object NumberFormatter {

    fun formatNumber(value: Any, minDigits: Int = 0): String {
        val formatter = DecimalFormat()
        formatter.minimumFractionDigits = minDigits
        return formatter.format(value)
    }

    fun formatPrice(
        value: Double?,
        currencyCode: String = "USD",
        digits: Int = 0
    ): String {
        return formatPriceValue(value ?: 0.0, currencyCode, digits)
    }

    private fun formatPriceValue(value: Any, currencyCode: String, digits: Int): String {
        val formatter = DecimalFormat.getCurrencyInstance()
        formatter.currency = Currency.getInstance(currencyCode)
        formatter.minimumFractionDigits = digits
        return formatter.format(value)
    }
}
