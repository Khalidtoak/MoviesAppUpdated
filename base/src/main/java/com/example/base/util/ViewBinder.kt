package com.example.base.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding

/**
 * Created by khalidtoak on 1/19/21.
 */
interface ViewBinder<T : ViewBinding> {

    val binding: T?

    /**
     * Saves the binding for cleanup on onDestroy
     */
    fun initBinding(binding: T, fragment: Fragment): View

    /**
     * @throws IllegalStateException if not currently holding a
     * ViewBinding (when called outside of an active fragment's lifecycle)
     * similar to what requireActivity does
     */
    fun requireBinding(): T
}

class ViewBinderImpl<T : ViewBinding> : ViewBinder<T>, LifecycleObserver {

    override var binding: T? = null
    private var lifecycle: Lifecycle? = null

    private lateinit var fragmentName: String

    /**
     * To not leak memory we nullify the binding when the view is destroyed.
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroyView() {
        lifecycle?.removeObserver(this) // not mandatory, but preferred
        lifecycle = null
        binding = null
    }

    override fun requireBinding() =
        binding ?: throw IllegalStateException("Cannot access binding outside $fragmentName lifecycle")

    override fun initBinding(binding: T, fragment: Fragment): View {
        this.binding = binding
        lifecycle = fragment.viewLifecycleOwner.lifecycle
        lifecycle?.addObserver(this)
        fragmentName = fragment::class.simpleName ?: "N/A"
        return binding.root
    }
}