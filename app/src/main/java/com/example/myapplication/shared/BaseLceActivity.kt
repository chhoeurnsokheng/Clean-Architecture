/*
 * BaseLceActivity.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.example.myapplication.R
import com.example.myapplication.shared.ErrorType
import com.example.myapplication.utils.extension.viewModels
import com.icekh.ugamer.shared.lce.view.LceEmptyView
import com.icekh.ugamer.shared.lce.view.LceErrorView
import com.icekh.ugamer.shared.lce.view.LceLayout
import com.icekh.ugamer.shared.lce.view.LceLoadingView
import com.icekh.ugamer.shared.lce.view.LceState
import com.icekh.ugamer.shared.lce.view.LceState.ContentState
import kotlin.reflect.KClass

abstract class BaseLceActivity<RB, CB, T, VM>(
    viewModelClass: KClass<VM>
) : AppCompatActivity() where RB : ViewBinding, CB : ViewBinding, VM : BaseLceViewModel<T> {

    protected lateinit var rootBinding: RB
    protected lateinit var binding: CB

    protected val viewModel: VM by viewModels(viewModelClass)
    protected var toolbar: Toolbar? = null
    protected lateinit var lceLayout: LceLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = setupRootBinding()
        setContentView(rootBinding.root)
        setupViewModel()
        setupInitialValue()
        setupLceLayout()
        setupToolbarView()
    }

    protected abstract fun setupRootBinding(): RB

    @LayoutRes
    protected abstract fun getContentLayoutId(): Int

    protected open fun setupInitialValue() {}

    protected open fun setupToolbarView() {
        toolbar = findViewById(R.id.toolbar)
        toolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    protected open fun setupLceLayout() {
        lceLayout = findViewById(R.id.lceLayout)
        lceLayout.setupLayout(
            contentLayoutId = getContentLayoutId(),
            loadingView = setupLoadingView(),
            emptyView = setupEmptyView(),
            errorView = setupErrorView()
        )
        val contentView = lceLayout.contentView.getChildAt(0)
        binding = setupContentBinding(contentView)
    }

    protected abstract fun setupContentBinding(contentView: View): CB

    protected open fun setupLoadingView(): LceLoadingView? = null

    protected open fun setupEmptyView(): LceEmptyView? = null

    protected open fun setupErrorView(): LceErrorView? = null

    @CallSuper
    protected open fun setupViewModel() {
        viewModel.dataState.observeState(
            onLoading = ::showLoading,
            onSuccess = ::showContent,
            onEmpty = ::showEmpty,
            onError = ::showError
        )
    }

    protected open fun changeState(state: LceState) {
        lceLayout.changeState(state)
    }

    protected open fun showLoading(isTranslucent: Boolean = false) {
        changeState(LceState.LoadingState(isTranslucent))
    }

    protected open fun showContent(data: T) {
        changeState(ContentState)
    }

    protected open fun showEmpty() {
        changeState(LceState.EmptyState)
    }

    protected open fun showError(errorType: ErrorType) {
        changeState(LceState.ErrorState(errorType))
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.dataState.removeObservers(this)
    }
}
