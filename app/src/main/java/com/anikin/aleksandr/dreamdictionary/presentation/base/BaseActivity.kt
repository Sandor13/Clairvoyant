package com.anikin.aleksandr.dreamdictionary.presentation.base

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.anikin.aleksandr.dreamdictionary.R
import com.anikin.aleksandr.dreamdictionary.data.errors.NoAuthException
import com.anikin.aleksandr.dreamdictionary.view.base.BaseViewState
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity<T, S : BaseViewState<T>> : AppCompatActivity() {

    private val RC_SIGN_IN = 458

    abstract val model: BaseViewModel<T, S>
    abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        model.getViewState().observe(this, Observer<S> { t ->
            t?.apply {
                data?.let { renderData(it) }
                error?.let { renderError(it) }
            }
        })
    }

    protected fun renderError(error: Throwable) {
        when (error) {
            is NoAuthException -> startLoginActivity()
            else -> error.message?.let { showError(it) }
        }
    }

    abstract fun renderData(data: T)

    //FIXME main_recyclerview
    protected fun showError(error: String) {
        Snackbar.make(main_recyclerview, error, Snackbar.LENGTH_INDEFINITE).apply {
            setAction(R.string.snackbar_button_ok) { this.dismiss() }
            show()
        }
    }

    private fun startLoginActivity() {
        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.android_robot)
                .setTheme(R.style.LoginStyle)
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK) {
            finish()
        }
    }
}
