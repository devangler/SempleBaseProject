package com.sempleprojectbase

import android.app.Application
import com.sempleprojectbase.koin.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @Author: Kamran Khan
 * @Date: 01,August,2023
 * @Accounts
 *      -> https://stackoverflow.com/users/17921670/kamran-khan
 */
class AppClass:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()


    }
    private fun initKoin() {
        startKoin {
            androidContext(this@AppClass)
            modules(modulesList)
        }
    }
}