package company.vk.education.androidcourse.rememberthepills.schedule.view.adapter

import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseDiffCallback
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.schedule.view.adapter.item.ScheduleListDataItem

class ScheduleDiffUtilCallback : BaseDiffCallback() {

    override fun getChangePayload(oldItem: BaseDataItem, newItem: BaseDataItem): Any? {
        return if (oldItem is ScheduleListDataItem && newItem is ScheduleListDataItem) {
            newItem
        } else {
            null
        }
    }
}