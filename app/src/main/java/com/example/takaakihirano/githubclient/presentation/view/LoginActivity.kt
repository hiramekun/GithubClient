package com.example.takaakihirano.githubclient.presentation.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.example.takaakihirano.githubclient.R
import com.example.takaakihirano.githubclient.extensions.toast
import com.example.takaakihirano.githubclient.presentation.navigation.Navigator
import com.example.takaakihirano.githubclient.presentation.presenter.LoginPresenter

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(), LoginView {

    companion object {
        fun createIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    private val presenter = LoginPresenter()
    private val pwEditText by lazy { findViewById<EditText>(R.id.password_edit_text) }
    private val signInButton by lazy { findViewById<Button>(R.id.email_sign_in_button) }
    private val emailTextView by lazy { findViewById<AutoCompleteTextView>(R.id.email_text_view) }
    private val loginForm by lazy { findViewById<ScrollView>(R.id.login_form) }
    private val loginProgress by lazy { findViewById<ProgressBar>(R.id.login_progress) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.initialize(this)
        presenter.outRequest.subscribe({
            Navigator.navigateToMain(this)
        }) {
            toast(getString(R.string.failed_to_login))
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }


    override fun renderView() {
        pwEditText.setOnEditorActionListener({ _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
            }
            false
        })

        signInButton.setOnClickListener { attemptLogin() }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    override fun attemptLogin() {
        // Reset errors.
        emailTextView.error = null
        pwEditText.error = null

        // Store values at the time of the login attempt.
        val emailStr = emailTextView.text.toString()
        val passwordStr = pwEditText.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordStr) && !isPasswordValid(passwordStr)) {
            pwEditText.error = getString(R.string.error_invalid_password)
            focusView = pwEditText
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            emailTextView.error = getString(R.string.error_field_required)
            focusView = emailTextView
            cancel = true
        } else if (!isEmailValid(emailStr)) {
            emailTextView.error = getString(R.string.error_invalid_email)
            focusView = emailTextView
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            // todo: implement login or sign up
            presenter.requestAuth(true)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return email.contains("@")
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    override fun showProgress(show: Boolean) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        loginForm.visibility = if (show) View.GONE else View.VISIBLE
        loginForm.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        loginForm.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

        loginProgress.visibility = if (show) View.VISIBLE else View.GONE
        loginProgress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {

                    }
                })
    }
}
