package com.splyza.testapp.core.network

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

/**
Sample code for uses [networkBoundResource] from repository class

fun getItems(request: MyRequest): Flow<Resource<List<MyItem>>> {
return networkBoundResource(
query = { dao.queryAll() },
fetch = { retrofitService.getItems(request) },
saveFetchResult = { items -> dao.insert(items) }
)
}
 */

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow<Resource<ResultType>> {
    emit(Resource.Loading(null))
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}

/**
Sample code for uses [safeApiCall] from repository class

suspend fun getBestInstitute(): Resource<BestInstituteResponse> {
return safeApiCall(dispatcher.IO) { teachingService.getBestInstitutes() }
}
 */
suspend fun <T : Any> safeApiCall(
    dispatcher: CoroutineContext,
    apiCall: suspend () -> Response<T>
): Resource<T> {
    return withContext(dispatcher) {
        try {
            safeApiResult(apiCall)
        } catch (throwable: Throwable) {
            Resource.Error(throwable)
        }
    }
}

// It will call the network API and return [Resource] as result.
internal suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): Resource<T> {
    val response = call.invoke()

    return if (response.isSuccessful) {
        val body = response.body()

        if (body == null) {
            Resource.Error(getErrorMessage(response.errorBody()))
        } else {
            Resource.Success(body)
        }
    } else {
        Resource.Error(getErrorMessage(response.errorBody()))
    }
}

// This method will read API error message from JsonObject and return as a Throwable
internal fun getErrorMessage(errorBody: ResponseBody?): Throwable {
    errorBody?.let {
        JSONObject(it.string()).apply {
            return Throwable(getString("message"))
        }
    }
    return Throwable("Unknown Error")
}