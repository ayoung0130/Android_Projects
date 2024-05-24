package com.example.marvel.exception

class NetworkFailureException(message: String? = "") : Exception(message)

class LoadErrorException(message: String? = "") : Exception(message)