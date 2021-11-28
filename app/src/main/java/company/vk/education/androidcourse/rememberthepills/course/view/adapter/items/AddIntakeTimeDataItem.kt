package company.vk.education.androidcourse.rememberthepills.course.view.adapter.items

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.CourseViewType

class AddIntakeTimeDataItem(
    val id: Int,
    val addIntakeTimeHandler: () -> Unit
) : BaseDataItem, BasePayload {
    override var viewType: Int = CourseViewType.addIntakeTime.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is AddIntakeTimeDataItem) {
            return true
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is AddIntakeTimeDataItem) {
            id == item.id
        } else {
            false
        }
    }
}