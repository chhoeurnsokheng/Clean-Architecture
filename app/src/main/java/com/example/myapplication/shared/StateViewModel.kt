///*
// * BaseStateViewModel.kt
// * UGamer
// *
// * Created by tesopheak on 04/19/2021.
// * Copyright (c) 2021 ICE Electronics. All rights reserved.
// */
//
//package com.icekh.ugamer.shared.lce
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.icekh.ugamer.domain.common.UseCase
//import com.icekh.ugamer.shared.State
//
//abstract class StateViewModel<S : State>(vararg useCases: UseCase) : ViewModel() {
//
//    private val _state: MutableLiveData<S> = MutableLiveData()
//    val state: LiveData<S> get() = _state
//
//    private val useCaseList: MutableList<UseCase> = mutableListOf()
//
//    init {
//        useCaseList.addAll(
//            useCases.map {
//                it.attachToLifecycle()
//                it
//            }
//        )
//    }
//
//    protected fun emitState(newState: S) {
//        _state.value = newState
//    }
//
//    protected fun emitStateBackground(newState: S) {
//        _state.postValue(newState)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        useCaseList.forEach { it.dispose() }
//    }
//}
