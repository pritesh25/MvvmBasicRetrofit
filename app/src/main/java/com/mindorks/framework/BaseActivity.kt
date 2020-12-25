package com.mindorks.framework

import android.content.Context
import android.net.*
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private val mTAG = "BaseActivity"
    var isInternet = false
    private var connectivityManager: ConnectivityManager? = null
    private val networkCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            Log.d(mTAG, "(onAvailable) isInternet")
            isInternet = true
            toasty(applicationContext, "Internet Available")
        }

        override fun onLost(network: Network) {
            Log.d(mTAG, "(onLost) isInternet")
            isInternet = false
            toasty(applicationContext, "No Internet")
        }
    }

    override fun onStart() {
        super.onStart()
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        registerCallback()
    }

    override fun onResume() {
        super.onResume()
        registerCallback()
    }

    override fun onPause() {
        super.onPause()
        unregisterCallback()
    }

    private fun registerCallback() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) /* api 21 or above */ {
                connectivityManager?.registerNetworkCallback(
                    NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                        .build(), networkCallback
                )
            } else {
                Log.d(mTAG, "(onResume) below api 21")
                val networkInfo: NetworkInfo = connectivityManager?.activeNetworkInfo!!
                if (networkInfo.isConnected) {
                    isInternet = true
                    Log.d(mTAG, "(below api 24)connected to internet")
                } else {
                    isInternet = false
                    Log.d(mTAG, "(below api 24)not connected to internet")
                }
            }
        } catch (e: Exception) {
            Log.d(mTAG, "(onResume) catch error = ${e.message}")
        }
    }

    private fun unregisterCallback() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                connectivityManager?.unregisterNetworkCallback(networkCallback)
            } else {
                Log.d(mTAG, "(onPause) below api 24")
            }
        } catch (e: Exception) {
            Log.d(mTAG, "(onPause) catch error = ${e.message}")
        }
    }

}