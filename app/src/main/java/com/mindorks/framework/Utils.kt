package com.mindorks.framework

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import android.widget.Toast

fun isNetworkConnected(context: Context): Boolean {
    var result = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        /*
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        Log.d("isNetworkConnected", "wifi = ${activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)}")
        Log.d("isNetworkConnected", "cellular = ${activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)}")
        Log.d("isNetworkConnected", "ethernet = ${activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)}")

        result = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        */
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build(), object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) {

                    Log.d("isNetworkConnected", "(onAvailable) isInternet")
                    result = true

                }

                override fun onLost(network: Network) {

                    Log.d("isNetworkConnected", "(onLost) isInternet}")
                    result = false

                }
            }
        )

    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }
    return result
}

fun toasty(cxt: Context, message: String) {
    Toast.makeText(cxt, message, Toast.LENGTH_SHORT).show()
}

const val INTERNET_CONNECTED = 1
const val INTERNET_DISCONNECTED = 0
const val INTERNET_UNDEFINED = -1