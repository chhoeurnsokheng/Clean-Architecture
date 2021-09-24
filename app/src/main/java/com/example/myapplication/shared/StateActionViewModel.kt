///*
// * StateEventViewModel.kt
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
//import com.icekh.ugamer.domain.common.UseCase
//import com.icekh.ugamer.shared.Action
//import com.icekh.ugamer.shared.Event
//import com.icekh.ugamer.shared.State
//
//abstract class StateActionViewModel<S : State, A : Action>(vararg useCases: UseCase) :
//    StateViewModel<S>(*useCases) {
//
//    private val _action: MutableLiveData<Event<A>> = MutableLiveData()
//    val action: LiveData<Event<A>> get() = _action
//
//    protected fun emitAction(newAction: A) {
//        _action.value = Event(newAction)
//    }
//
//    protected fun emitActionBackground(newAction: A) {
//        _action.postValue(Event(newAction))
//    }
//}
