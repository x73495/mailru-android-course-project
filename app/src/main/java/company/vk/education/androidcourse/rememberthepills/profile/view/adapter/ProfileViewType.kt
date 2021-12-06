package company.vk.education.androidcourse.rememberthepills.profile.view.adapter

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseViewType

class ProfileViewType {
    class ListItem: BaseViewType()

    companion object {
        val listItem = ProfileViewType.ListItem()
    }
}