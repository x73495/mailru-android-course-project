package company.vk.education.androidcourse.rememberthepills.course.view.adapter

import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseDiffCallback
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.*
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.AddIntakeTimeDataItem
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.CourseDrugTitleDataItem
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.IntakeTimeDataItem

class CourseDiffUtilCallback : BaseDiffCallback() {

    override fun getChangePayload(oldItem: BaseDataItem, newItem: BaseDataItem): Any? {
        return if (oldItem is AutocomplitedTextFieldDataItem && newItem is AutocomplitedTextFieldDataItem) {
            newItem
        } else if (oldItem is TextedTextFieldDataItem && newItem is TextedTextFieldDataItem) {
            newItem
        } else if (oldItem is DatedTextFieldDataItem && newItem is DatedTextFieldDataItem) {
            newItem
        } else if (oldItem is NumberedTextFieldDataItem && newItem is NumberedTextFieldDataItem) {
            newItem
        } else if (oldItem is SectionHeaderDataItem && newItem is SectionHeaderDataItem) {
            newItem
        } else if (oldItem is AddIntakeTimeDataItem && newItem is AddIntakeTimeDataItem) {
            newItem
        } else if (oldItem is CourseDrugTitleDataItem && newItem is CourseDrugTitleDataItem) {
            newItem
        } else if (oldItem is IntakeTimeDataItem && newItem is IntakeTimeDataItem) {
            newItem
        } else {
            null
        }
    }
}