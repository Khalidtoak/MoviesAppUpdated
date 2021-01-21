package com.example.base.mvx

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * Created by khalidtoak on 1/18/21.
 */

abstract class BaseViewModel<SS : ScreenState>(initialState : SS) : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<SS>()
    val stateLiveData = stateMutableLiveData as LiveData<SS>

    protected var state by Delegates.observable(initialState) { _, _, newValue ->
        stateMutableLiveData.value = newValue
    }

    fun publish(state : SS){
        this.state = state
    }

    /**
     * coroutine function with exception handling built in
     *
     * @param suspendFunction0 : suspending function to be executed on success
     * @param failure: handles exception
     * @return the coroutine job incase it is to be canceled**/
    fun launchCoroutine(suspendFunction0: suspend () -> Unit,
                        failure: (Throwable) -> Unit = { it.printStackTrace() }): Job {
        return viewModelScope.launch {
            runCatching {
                suspendFunction0()
            }.onFailure {
                if (it is CancellationException)
                    return@launch
                failure(it)
                Timber.i("operation failed with $it")
            }
        }
    }

    fun cancelCoroutine(job: Job?) {
        job?.cancel()
    }

}