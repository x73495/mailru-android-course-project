package company.vk.education.androidcourse.rememberthepills.drugList.view.adapter

import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseDiffCallback
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.item.DrugListDataItem

class DrugListDiffUtilCallback: BaseDiffCallback() {

    override fun getChangePayload(oldItem: BaseDataItem, newItem: BaseDataItem): Any? {
        return if (oldItem is DrugListDataItem && newItem is DrugListDataItem) {
            newItem
        } else {
            null
        }
    }
}