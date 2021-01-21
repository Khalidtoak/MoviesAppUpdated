package com.example.network.base

/**
 * Created by khalidtoak on 1/19/21.
 */
data class BaseErrorResponse(
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)