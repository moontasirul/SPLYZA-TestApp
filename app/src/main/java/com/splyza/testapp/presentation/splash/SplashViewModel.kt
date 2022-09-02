package com.splyza.testapp.presentation.splash

import android.os.Handler
import android.os.Looper
import com.splyza.testapp.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<ISplashNavigator>() {

    fun splashCount() {
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                navigator.openHomeScreen()
            }, 3000)
        }
    }
}