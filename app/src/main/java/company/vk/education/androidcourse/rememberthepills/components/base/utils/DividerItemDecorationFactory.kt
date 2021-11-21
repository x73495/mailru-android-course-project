package company.vk.education.androidcourse.rememberthepills.components.base.utils

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import company.vk.education.androidcourse.rememberthepills.R

class DividerItemDecorationFactory {

    fun create(context: Context, orientation: Int): DividerItemDecoration {
        val decorator = DividerItemDecoration(context, orientation)
        context.getDrawable(R.drawable.divider_16)?.let { decorator.setDrawable(it) }
        return decorator
    }
}