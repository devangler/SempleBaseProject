package com.sempleprojectbase.managers

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.sempleprojectbase.R
import com.sempleprojectbase.managers.RemoteConstants.INTER_SPLASH_KEY
import com.sempleprojectbase.managers.RemoteConstants.NATIVE_SPLASH_KEY

/**
 * @Author: Kamran Khan
 * @Date: 01,August,2023
 * @Accounts
 *      -> https://stackoverflow.com/users/17921670/kamran-khan
 */
class RemoteConfiguration(private val internetManager: InternetManager) {

    private val configTag = "TAG_REMOTE_CONFIG"

    fun checkRemoteConfig(callback: (fetchSuccessfully: Boolean) -> Unit) {
        if (internetManager.isInternetConnected) {
            val remoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings { minimumFetchIntervalInSeconds = 2 }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
            fetchRemoteValues(callback)
        } else {
            Log.d(configTag, "checkRemoteConfig: Internet Not Found!")
            callback.invoke(false)
        }
    }

    private fun fetchRemoteValues(callback: (fetchSuccessfully: Boolean) -> Unit) {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            if (it.isSuccessful) {
                try {
                    updateRemoteValues(callback)
                } catch (ex: Exception) {

                    Log.d(configTag, "fetchRemoteValues: ${it.exception}")
                    callback.invoke(false)
                }
            } else {
                Log.d(configTag, "fetchRemoteValues: ${it.exception}")
                callback.invoke(false)
            }
        }.addOnFailureListener {
            Log.d(configTag, "fetchRemoteValues: ${it.message}")
            callback.invoke(false)
        }
    }

    @Throws(Exception::class)
    private fun updateRemoteValues(callback: (fetchSuccessfully: Boolean) -> Unit) {
        val remoteConfig = Firebase.remoteConfig

        RemoteConstants.rcvInterSplash = remoteConfig[INTER_SPLASH_KEY].asLong().toInt()
        RemoteConstants.rcvNativeSplash = remoteConfig[NATIVE_SPLASH_KEY].asLong().toInt()

        Log.d(configTag, "checkRemoteConfig: Fetched Successfully")
        callback.invoke(true)
    }
}