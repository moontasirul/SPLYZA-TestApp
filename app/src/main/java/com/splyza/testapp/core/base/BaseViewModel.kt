package com.splyza.testapp.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

/**
 * All [ViewModel] class must inherit this [BaseViewModel] class
 */
abstract class BaseViewModel<N : IBaseNavigator> : ViewModel(), CoroutineScope {
    var isLoading = MutableStateFlow(false)


    var titleText = MutableStateFlow<String>("")

    var isToolbarShow = MutableStateFlow<Boolean>(false)
    var isBackButtonShow = MutableStateFlow<Boolean>(true)

    private val _job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + _job

    override fun onCleared() {
        _job.cancel()
        super.onCleared()
    }


    private lateinit var mNavigator: N

    /**
     * Get Navigator
     */
    protected val navigator: N
        get() {
            return mNavigator
        }

    /**
     * Set Navigator
     */
    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }
}