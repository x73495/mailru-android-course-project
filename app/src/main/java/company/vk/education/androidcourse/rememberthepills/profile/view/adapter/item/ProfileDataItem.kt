package company.vk.education.androidcourse.rememberthepills.profile.view.adapter.item

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.profile.view.adapter.ProfileViewType

class ProfileDataItem(
    val id: String,
    val title: String,
    val selectionHandler: () -> Unit
) : BaseDataItem, BasePayload {
    override var viewType: Int = ProfileViewType.listItem.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is ProfileDataItem) {
            return title == item.title
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is ProfileDataItem) {
            id == item.id
        } else {
            false
        }
    }
}