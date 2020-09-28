package com.workday.codingtask.data

/**
 * @CreatedBy Nathan N
 *
 *         Author Email: Nathan.nakhjavani@gmail.com
 *         Created on: 2020-09-27
 */
sealed class OperationResult<out T> {
    data class Success<T>(val response: T?) : OperationResult<T>()
    data class Error(val exception: Exception) : OperationResult<Nothing>()
}