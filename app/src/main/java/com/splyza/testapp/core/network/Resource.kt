package com.splyza.testapp.core.network

/**
 * A generic class that contains data and status about loading this data.
 */
sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable?, data: T? = null) : Resource<T>(data, throwable)
}

// Extension function to read success state using kotlin higher-order function.
suspend fun <T : Any> Resource<T>.onSuccess(
    executable: suspend (T) -> Unit
): Resource<T> = apply {
    if (this is Resource.Success<T>) {
        data?.let { executable(it) }
    }
}

// Extension function to read loading state using kotlin higher-order function.
suspend fun <T> Resource<T>.onLoading(
    executable: suspend (data: T?) -> Unit
): Resource<T> = apply {
    if (this is Resource.Error<T>) {
        executable(data)
    }
}

// Extension function to read error state using kotlin higher-order function.
suspend fun <T> Resource<T>.onError(
    executable: suspend (e: Throwable?, data: T?) -> Unit
): Resource<T> = apply {
    if (this is Resource.Error<T>) {
        executable(error, data)
    }
}