/*
 * StateFragment.kt
 * UGamer
 *
 * Created by tesopheak on 04/19/2021.
 * Copyright (c) 2021 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.icekh.ugamer.R
import com.icekh.ugamer.shared.ErrorType
import com.icekh.ugamer.shared.lce.view.LceEmptyView
import com.icekh.ugamer.shared.lce.view.LceErrorView
import com.icekh.ugamer.shared.lce.view.LceLayout
import com.icekh.ugamer.shared.lce.view.LceLoadingView
import com.icekh.ugamer.shared.lce.view.LceState
import com.icekh.ugamer.shared.lce.view.LceState.ContentState
import com.icekh.ugamer.utils.extension.viewModels
import kotlin.reflect.KClass

abstract class StateFragment<RB, CB, VM>(
    viewModelClass: KClass<VM>
) : Fragment() where RB : ViewBinding, CB : ViewBinding, VM : ViewModel {

    private var _rootBinding: RB? = null
    protected val rootBinding get() = _rootBinding!!

    private var _binding: CB? = null
    protected val binding get() = _binding!!

    protected val viewModel: VM by viewModels(viewModelClass)
    protected lateinit var lceLayout: LceLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _rootBinding = setupRootBinding(inflater, container)
        return rootBinding.root
    }

    protected abstract fun setupRootBinding(inflater: LayoutInflater, container: ViewGroup?): RB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLceLayout(view)
    }

    @LayoutRes
    protected abstract fun getContentLayoutId(): Int

    protected open fun setupLceLayout(view: View) {
        lceLayout = view.findViewById(R.id.lceLayout)
        lceLayout.setupLayout(
            contentLayoutId = getContentLayoutId(),
            loadingView = setupLoadingView(),
            emptyView = setupEmptyView(),
            errorView = setupErrorView()
        )
        val contentView = lceLayout.contentView.getChildAt(0)
        _binding = setupContentBinding(contentView)
    }

    protected abstract fun setupContentBinding(contentView: View): CB

    protected open fun setupLoadingView(): LceLoadingView? = null

    protected open fun setupEmptyView(): LceEmptyView? = null

    protected open fun setupErrorView(): LceErrorView? = null

    protected open fun changeState(state: LceState) {
        lceLayout.changeState(state)
    }

    protected open fun showLoading(isTranslucent: Boolean) {
        changeState(LceState.LoadingState(isTranslucent))
    }

    protected open fun showContent() {
        changeState(ContentState)
    }

    protected open fun showEmpty() {
        changeState(LceState.EmptyState)
    }

    protected open fun showError(error: ErrorType) {
        changeState(LceState.ErrorState(error))
    }
}
