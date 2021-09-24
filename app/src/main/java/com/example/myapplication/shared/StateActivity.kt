///*
// * StateActivity.kt
// * UGamer
// *
// * Created by tesopheak on 04/19/2021.
// * Copyright (c) 2021 ICE Electronics. All rights reserved.
// */
//
//package com.icekh.ugamer.shared.lce
//
//import android.os.Bundle
//import android.view.MenuItem
//import android.view.View
//import androidx.annotation.LayoutRes
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.Toolbar
//import androidx.lifecycle.ViewModel
//import androidx.viewbinding.ViewBinding
//import com.icekh.ugamer.R
//import com.icekh.ugamer.shared.ErrorType
//import com.icekh.ugamer.shared.lce.view.LceEmptyView
//import com.icekh.ugamer.shared.lce.view.LceErrorView
//import com.icekh.ugamer.shared.lce.view.LceLayout
//import com.icekh.ugamer.shared.lce.view.LceLoadingView
//import com.example.myapplication.shared.view.view.LceState
//import com.example.myapplication.shared.view.view.LceState.ContentState
//import com.icekh.ugamer.utils.extension.viewModels
//import kotlin.reflect.KClass
//
//abstract class StateActivity<RB, CB, VM>(
//    viewModelClass: KClass<VM>
//) : AppCompatActivity() where RB : ViewBinding, CB : ViewBinding, VM : ViewModel {
//
//    protected lateinit var rootBinding: RB
//    protected lateinit var binding: CB
//
//    protected val viewModel: VM by viewModels(viewModelClass)
//    protected var toolbar: Toolbar? = null
//    protected lateinit var lceLayout: LceLayout
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        rootBinding = setupRootBinding()
//        setContentView(rootBinding.root)
//        setupLceLayout()
//        setupToolbar()
//    }
//
//    protected abstract fun setupRootBinding(): RB
//
//    @LayoutRes
//    protected abstract fun getContentLayoutId(): Int
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            onBackPressed()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    protected open fun setupToolbar() {
//        toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.apply {
//            setDisplayHomeAsUpEnabled(true)
//            setDisplayShowTitleEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
//        }
//    }
//
//    protected open fun setupLceLayout() {
//        lceLayout = findViewById(R.id.lceLayout)
//        lceLayout.setupLayout(
//            contentLayoutId = getContentLayoutId(),
//            loadingView = setupLoadingView(),
//            emptyView = setupEmptyView(),
//            errorView = setupErrorView()
//        )
//        val contentView = lceLayout.contentView.getChildAt(0)
//        binding = setupContentBinding(contentView)
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
//    protected open fun changeState(state: LceState) {
//        lceLayout.changeState(state)
//    }
//
//    protected open fun showLoading(isTranslucent: Boolean) {
//        changeState(LceState.LoadingState(isTranslucent))
//    }
//
//    protected open fun showContent() {
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
//}
