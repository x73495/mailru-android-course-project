package company.vk.education.androidcourse.rememberthepills.course.view.adapter.items

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.CourseViewType

class IntakeTimeDataItem (
    val id: String,
    var timeString: String,
    val removeTimeHandler: () -> Unit
) : BaseDataItem, BasePayload {
    override var viewType: Int = CourseViewType.intakeTime.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is IntakeTimeDataItem) {
            return timeString == item.timeString
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is IntakeTimeDataItem) {
            id == item.id
        } else {
            false
        }
    }
}