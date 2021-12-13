package company.vk.education.androidcourse.rememberthepills.components.form.model

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload

class SectionHeaderDataItem (
    val id: String,
    val text: String?
) : BaseDataItem, BasePayload {

    override var viewType: Int = FormViewType.sectionHeader.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is SectionHeaderDataItem) {
            text == item.text
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is SectionHeaderDataItem) {
            id == item.id
        } else {
            false
        }
    }
}