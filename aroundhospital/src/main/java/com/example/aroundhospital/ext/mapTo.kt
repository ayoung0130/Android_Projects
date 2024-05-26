package com.example.aroundhospital.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import retrofit2.Response
import java.lang.Exception

fun <T> Response<T>.mapTo(): T =
    if (isSuccessful) {
        body() ?: throw Exception("[${code()} - ${raw()}]")
    } else throw Exception("[${code()} - ${raw()}]")


sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable? = null) : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this.map<T, Result<T>> {
        Result.Success(it)
    }.catch {
        Result.Error(it)
    }
}