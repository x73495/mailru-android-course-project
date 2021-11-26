package company.vk.education.androidcourse.rememberthepills.components.base.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() = (this.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)
    ?.hideSoftInputFromWindow(this.windowToken, 0)

fun View.showKeyboard() {
    this.apply {
        requestFocus()
        (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)
            ?.showSoftInput(this, 0)
    }
}