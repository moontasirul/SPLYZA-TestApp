package com.splyza.testapp.core.network

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * [ConnectivityChecker] provide [callbackFlow] to observe the network connectivity.
 */
class ConnectivityChecker(
    private val connectivityManager: ConnectivityManager
) {
    @SuppressLint("MissingPermission")
    val networkStatus = callbackFlow<Boolean> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                trySend(false)
            }

            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }.distinctUntilChanged()
}

/**
 * Extension function will drop first emit value.
 */
suspend fun ConnectivityChecker.onNetworkChange(action: (Boolean) -> Unit) {
    networkStatus.collect { action(it) }
}