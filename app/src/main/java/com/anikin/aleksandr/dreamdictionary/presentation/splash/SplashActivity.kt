package com.anikin.aleksandr.dreamdictionary.presentation.splash

import android.arch.lifecycle.ViewModelProviders
import android.os.Handler
import com.anikin.aleksandr.dreamdictionary.R
import com.anikin.aleksandr.dreamdictionary.presentation.base.BaseActivity
import com.anikin.aleksandr.dreamdictionary.presentation.main.MainActivity

class SplashActivity : BaseActivity<Boolean?, SplashViewState>() {

    private val START_DELAY = 1000L

    override val model: SplashViewModel by lazy { ViewModelProviders.of(this).get(SplashViewModel::class.java) }

    override val layoutRes: Int = R.layout.activity_splash

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ model.requestUser() }, START_DELAY)
    }

    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}