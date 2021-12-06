package company.vk.education.androidcourse.rememberthepills.profile.view.adapter

import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolderAbstractFactory

class ProfileViewHolderFactory : BaseViewHolderAbstractFactory() {
    override fun makeViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder {
        return when (viewType) {
            ProfileViewType.listItem.viewType -> ProfileViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }
}