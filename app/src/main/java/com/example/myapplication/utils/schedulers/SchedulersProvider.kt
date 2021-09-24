/*
 * SchedulersProvider.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.utils.schedulers

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersProvider {
    fun io(): Scheduler
    fun computation(): Scheduler
    fun main(): Scheduler
}
