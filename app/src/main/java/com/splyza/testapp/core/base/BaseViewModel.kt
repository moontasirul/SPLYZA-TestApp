package com.splyza.testapp.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * All [ViewModel] class must inherit this [BaseViewModel] class
 */
abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val _job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + _job

    override fun onCleared() {
        _job.cancel()
        super.onCleared()
    }
}