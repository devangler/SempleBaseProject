package com.sempleprojectbase.fragment.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.sempleprojectbase.BuildConfig
import com.sempleprojectbase.R


open class FragmentGeneral : Fragment() {

    private val generalTAG = "GeneralTAG"

    protected fun withDelay(delay: Long = 300, block: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(block, delay)
    }

    protected fun getResString(stringId: Int): String {
        return context?.resources?.getString(stringId) ?: ""
    }

    /* ---------- Toast ---------- */

    protected fun showToast(message: String) {
        activity?.let {
            try {
                it.runOnUiThread {
                    Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
                }
            } catch (ex: Exception) {

            }
        }
    }

    protected fun debugToast(message: String) {
        if (BuildConfig.DEBUG) {
            showToast(message)
        }
    }

    protected fun showToast(stringId: Int) {
        val message = getResString(stringId)
        showToast(message)
    }

    /* ---------- Snackbar ---------- */

    protected fun showSnackBar(message: String) {
        this.view?.let { v ->
            activity?.let {
                try {
                    it.runOnUiThread {
                        Snackbar.make(v, message, Snackbar.LENGTH_SHORT).show()
                    }
                } catch (ex: Exception) {

                }
            }
        }
    }

    /* ---------- Keyboard (Input Method) ---------- */

    protected fun showKeyboard() {
        try {
            val imm: InputMethodManager? = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        }catch (ex:Exception){

        }
    }

    protected fun hideKeyboard() {
        try {
            val inputMethodManager: InputMethodManager = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            val view: IBinder? = activity?.findViewById<View?>(android.R.id.content)?.windowToken
            inputMethodManager.hideSoftInputFromWindow(view, 0)
        } catch (ex: Exception) {

        }
    }
}