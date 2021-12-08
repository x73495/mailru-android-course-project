package company.vk.education.androidcourse.rememberthepills.schedule.view.adapter

import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolderAbstractFactory

class ScheduleListViewHolderFactory : BaseViewHolderAbstractFactory() {

    override fun makeViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder {
        return when(viewType) {
            ScheduleListViewType.listItem.viewType -> ScheduleListViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }
}