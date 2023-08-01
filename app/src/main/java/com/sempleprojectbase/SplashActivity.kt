package com.sempleprojectbase

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.sempleprojectbase.databinding.ActivitySplashBinding
import com.sempleprojectbase.koin.DIComponent

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    protected val diComponent by lazy { DIComponent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        fetchRemoteConfiguration()

    }
    fun nextActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun fetchRemoteConfiguration() {
        diComponent.remoteConfiguration.checkRemoteConfig {
            withDelay(2000) {
                lifecycleScope.launchWhenResumed {
                    nextActivity()
                }
            }
        }
    }
}