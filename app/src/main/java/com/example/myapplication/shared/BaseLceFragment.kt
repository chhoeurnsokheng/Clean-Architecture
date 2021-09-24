package com.example.myapplication.shared///*
// * BaseLceFragment.kt
// * UGamer
// *
// * Created by Sopheak Te on 08/13/2020.
// * Copyright (c) 2020 ICE Electronics. All rights reserved.
// */
//
//package com.icekh.ugamer.shared.lce
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.annotation.CallSuper
//import androidx.annotation.LayoutRes
//import androidx.appcompat.widget.Toolbar
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.viewbinding.ViewBinding
//import com.example.myapplication.R
//import com.example.myapplication.shared.ErrorType
//import com.icekh.ugamer.shared.lce.view.LceEmptyView
//import com.icekh.ugamer.shared.lce.view.LceErrorView
//import com.icekh.ugamer.shared.lce.view.LceLayout
//import com.icekh.ugamer.shared.lce.view.LceLoadingView
//import com.example.myapplication.shared.view.view.LceState
//import com.example.myapplication.shared.view.view.LceState.ContentState
//import kotlin.reflect.KClass
//
//abstract class BaseLceFragment<RB, CB, T, VM>(
//    viewModelClass: KClass<VM>
//) : Fragment() where RB : ViewBinding, CB : ViewBinding, VM : BaseLceViewModel<T> {
//
//    private var _rootBinding: RB? = null
//    protected val rootBinding get() = _rootBinding!!
//
//    private var _binding: CB? = null
//    protected val binding get() = _binding!!
//
//    protected val viewModel: VM by viewModels(viewModelClass)
//    protected var toolbar: Toolbar? = null
//    protected lateinit var lceLayout: LceLayout
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _rootBinding = setupRootBinding(inflater, container)
//        return rootBinding.root
//    }
//
//    protected abstract fun setupRootBinding(inflater: LayoutInflater, container: ViewGroup?): RB
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupViewModel()
//        setupInitialValue()
//        setupLceLayout(view)
//    }
//
//    @LayoutRes
//    protected abstract fun getContentLayoutId(): Int
//
//    protected open fun setupInitialValue() {}
//
//    protected open fun setupLceLayout(view: View) {
//        lceLayout = view.findViewById(R.id.lceLayout)
//        lceLayout.setupLayout(
//            contentLayoutId = getContentLayoutId(),
//            loadingView = setupLoadingView(),
//            emptyView = setupEmptyView(),
//            errorView = setupErrorView()
//        )
//        val contentView = lceLayout.contentView.getChildAt(0)
//        _binding = setupContentBinding(contentView)
//    }
//
//    protected abstract fun setupContentBinding(contentView: View): CB
//
//    protected open fun setupLoadingView(): LceLoadingView? = null
//
//    protected open fun setupEmptyView(): LceEmptyView? = null
//
//    protected open fun setupErrorView(): LceErrorView? = null
//
//    protected open fun setupToolbar() {
//        toolbar = view?.findViewById(R.id.toolbar)
//    }
//    @CallSuper
//    protected open fun setupViewModel() {
//        viewModel.dataState.observeState(
//            onLoading = ::showLoading,
//            onSuccess = ::showContent,
//            onEmpty = ::showEmpty,
//            onError = ::showError
//        )
//    }
//
//    protected open fun changeState(state: LceState) {
//        lceLayout = view?.findViewById(R.id.lceLayout)!!
//        lceLayout.changeState(state)
//    }
//
//    protected open fun showLoading(isTranslucent: Boolean) {
//        changeState(LceState.LoadingState(isTranslucent))
//    }
//
//    protected open fun showContent(data: T) {
//        changeState(ContentState)
//    }
//
//    protected open fun showEmpty() {
//        changeState(LceState.EmptyState)
//    }
//
//    protected open fun showError(error: ErrorType) {
//        changeState(LceState.ErrorState(error))
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        viewModel.dataState.removeObservers(this)
//    }
//}
