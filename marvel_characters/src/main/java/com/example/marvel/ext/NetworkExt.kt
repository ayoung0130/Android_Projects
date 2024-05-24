package com.example.marvel.ext

import com.example.marvel.exception.LoadErrorException
import com.example.marvel.exception.NetworkFailureException
import retrofit2.Response

fun <T> Response<T>.mapTo(): T =
    if (isSuccessful) {
        body() ?: throw LoadErrorException("[${code()} - ${raw()}]")
    } else throw NetworkFailureException("[${code()} - ${raw()}]")