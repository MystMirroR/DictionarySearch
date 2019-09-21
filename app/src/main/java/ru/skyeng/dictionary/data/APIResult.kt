package ru.skyeng.dictionary.data

sealed class APIResult<out T : Any> {
    class Success<out T : Any>(val data: T) : APIResult<T>()
    class Error(val exception: Throwable) : APIResult<Nothing>()
}