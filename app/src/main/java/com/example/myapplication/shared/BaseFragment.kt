/*
 * BaseFragment.kt
 * UGamer
 *
 * Created by Sopheak Te on 08/13/2020.
 * Copyright (c) 2020 ICE Electronics. All rights reserved.
 */

package com.icekh.ugamer.shared.lce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setupBinding(inflater, container)
        return binding.root
    }

    protected abstract fun setupBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
