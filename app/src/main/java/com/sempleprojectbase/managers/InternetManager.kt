package com.sempleprojectbase.managers

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * @Author: Kamran Khan
 * @Date: 01,August,2023
 * @Accounts
 *      -> https://stackoverflow.com/users/17921670/kamran-khan
 */
class InternetManager(private val connectivityManager: ConnectivityManager) {

    val isInternetConnected: Boolean
        get() {
            try {
                val network = connectivityManager.activeNetwork ?: return false
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
                return when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
                return false
            }
        }
}