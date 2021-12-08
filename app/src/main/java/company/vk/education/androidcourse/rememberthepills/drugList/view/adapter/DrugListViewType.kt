package company.vk.education.androidcourse.rememberthepills.drugList.view.adapter

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseViewType

class DrugListViewType {
    class ListItem: BaseViewType()

    companion object {
        val listItem = DrugListViewType.ListItem()
    }
}