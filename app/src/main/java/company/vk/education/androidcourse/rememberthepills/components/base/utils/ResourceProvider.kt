package company.vk.education.androidcourse.rememberthepills.components.base.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.core.content.ContextCompat

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes id: Int): String = context.getString(id)

    fun getString(@StringRes id: Int, vararg args: Any): String = context.getString(id, *args)

    fun getDrawable(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(context, id)

    fun getDimensionPixelSize(@DimenRes id: Int): Int = context.resources.getDimensionPixelSize(id)

    fun getDimension(@DimenRes id: Int): Float = context.resources.getDimension(id)

    fun getColor(@ColorRes id: Int): Int = ContextCompat.getColor(context, id)

    fun getInteger(@IntegerRes id: Int): Int = context.resources.getInteger(id)
}