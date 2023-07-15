package com.alselwi.sumimagesapi.util

sealed class ResourcePics<T>(val data: T? = null, val message:String? = null) {
    class Success<T>(data: T) : ResourcePics<T>(data = data)
    class Error<T>(message: String, data: T? = null ): ResourcePics<T>(data = data, message = message)
    class Loading<T>(data: T? = null): ResourcePics<T>(data)
}