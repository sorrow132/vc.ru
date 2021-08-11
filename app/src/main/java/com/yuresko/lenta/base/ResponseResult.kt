package com.yuresko.lenta.base

/**
 * A generic class that holds a value or an exception
 */
sealed class ResponseResult<out R> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Error(val exception: Exception) : ResponseResult<Nothing>()
}