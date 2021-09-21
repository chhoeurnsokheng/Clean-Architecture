/*
 * ViewModelExtension.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.example.myapplication.utils.extension

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

@MainThread
fun <VM : ViewModel> ComponentActivity.viewModels(
    clazz: KClass<VM>,
    factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(clazz, { viewModelStore }, factoryPromise)
}

// @MainThread
// fun <VM : ViewModel> Fragment.viewModels(
//    clazz: KClass<VM>,
//    ownerProducer: () -> ViewModelStoreOwner = { this },
//    factoryProducer: (() -> ViewModelProvider.Factory)? = null
// ) = createViewModelLazy(clazz, { ownerProducer().viewModelStore }, factoryProducer)
