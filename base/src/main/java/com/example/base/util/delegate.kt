package com.example.base.util

import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by khalidtoak on 1/21/21.
 */
inline fun <T> observer(
    initialValue: T,
    crossinline onChange: (newValue: T) -> Unit
):
        ReadWriteProperty<Any?, T> =
    object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) =
            onChange(newValue)
    }