package com.sempleprojectbase.koin

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.sempleprojectbase.managers.InternetManager
import com.sempleprojectbase.managers.RemoteConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * @Author: Kamran Khan
 * @Date: 01,August,2023
 * @Accounts
 *      -> https://stackoverflow.com/users/17921670/kamran-khan
 */
private val applicationScope = CoroutineScope(SupervisorJob())


private val managerModules = module {
    single { InternetManager(androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) }
}




private val firebaseModule = module {
    single { RemoteConfiguration(get()) }
}

val modulesList = listOf(managerModules, firebaseModule)