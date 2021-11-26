package company.vk.education.androidcourse.rememberthepills.components.base.viewHolder

import android.view.ViewGroup

abstract class BaseViewHolderAbstractFactory {
    abstract fun makeViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder
}