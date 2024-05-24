package com.android.kakao_book.exception

class NetworkFailureException(message: String? = "") : Exception(message)

class SearchErrorException(message: String? = "") : Exception(message)


class EmptyBodyException(message: String? = "") : Exception(message)
