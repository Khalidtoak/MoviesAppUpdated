package com.example.network.base

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import retrofit2.HttpException

/**
 * Created by khalidtoak on 1/20/21.
 */

fun parseHttpError(
    genericResponse: String
    = "Something went wrong. Please try again.",
    serverErrorMessage: String? = null
): (Throwable) -> String {
    return { throwable ->
        when (throwable) {
            is HttpException -> {
                when {
                    throwable.code() == 401 -> {
                        "you are not authorized to use this api"
                    }
                    throwable.code() in 500..504 -> {
                        serverErrorMessage ?: genericResponse
                    }
                    else -> {
                        try {
                            val moshi = Moshi.Builder().build()
                            val type = Types.newParameterizedType(BaseErrorResponse::class.java)
                            val adapter: JsonAdapter<BaseErrorResponse> = moshi.adapter(type)
                            val response =
                                adapter.fromJson(throwable.response()?.errorBody()!!.toString())
                            response!!.status_message
                        } catch (e: Exception) {
                            genericResponse
                        }
                    }
                }
            }
            else -> genericResponse
        }
    }
}