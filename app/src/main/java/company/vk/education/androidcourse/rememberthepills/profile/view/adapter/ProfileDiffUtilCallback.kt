package company.vk.education.androidcourse.rememberthepills.profile.view.adapter

import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseDiffCallback
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.profile.view.adapter.item.ProfileDataItem

class ProfileDiffUtilCallback : BaseDiffCallback() {
    override fun getChangePayload(oldItem: BaseDataItem, newItem: BaseDataItem): Any? {
        return if (oldItem is ProfileDataItem && newItem is ProfileDataItem) {
            newItem
        } else {
            null
        }
    }
}