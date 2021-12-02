package company.vk.education.androidcourse.rememberthepills.course.view.adapter.items

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.CourseViewType

class CourseDrugTitleDataItem(
    val id: String,
    val headline: String,
    val description: String
) : BaseDataItem, BasePayload {
    override var viewType: Int = CourseViewType.courseDrugTitle.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is CourseDrugTitleDataItem) {
            return headline == item.headline && description == item.description
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is CourseDrugTitleDataItem) {
            id == item.id
        } else {
            false
        }
    }
}