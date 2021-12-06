package company.vk.education.androidcourse.rememberthepills.schedule.view.adapter.item

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.schedule.view.adapter.ScheduleListViewType

class ScheduleListDataItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val failure: String?,
    val checked: Boolean,
    val checkingHandler: () -> Unit,
    val editHandler: () -> Unit
) : BaseDataItem, BasePayload {
    override var viewType: Int = ScheduleListViewType.listItem.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is ScheduleListDataItem) {
            return title == item.title &&
                    subtitle == item.subtitle &&
                    failure == item.failure &&
                    checked == item.checked
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is ScheduleListDataItem) {
            id == item.id
        } else {
            false
        }
    }
}