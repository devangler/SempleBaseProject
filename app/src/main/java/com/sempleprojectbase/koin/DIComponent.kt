package com.sempleprojectbase.koin

import com.sempleprojectbase.managers.InternetManager
import com.sempleprojectbase.managers.RemoteConfiguration
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * @Author: Kamran Khan
 * @Date: 01,August,2023
 * @Accounts
 *      -> https://stackoverflow.com/users/17921670/kamran-khan
 */
class DIComponent : KoinComponent {


    // Managers
    val internetManager by inject<InternetManager>()

    // Remote Configuration
    val remoteConfiguration by inject<RemoteConfiguration>()

}