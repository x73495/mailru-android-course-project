package company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.item

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.DrugListViewType

class DrugListDataItem(
    val id: String,
    val name: String,
    val type: String,
    val selectionHandler: () -> Unit
) : BaseDataItem, BasePayload {
    override var viewType: Int = DrugListViewType.listItem.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is DrugListDataItem) {
            return name == item.name && type == item.type
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is DrugListDataItem) {
            id == item.id
        } else {
            false
        }
    }
}