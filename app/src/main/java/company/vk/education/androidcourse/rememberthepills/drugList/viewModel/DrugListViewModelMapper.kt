package company.vk.education.androidcourse.rememberthepills.drugList.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListItem

class DrugListViewModelMapper(
    private val resourceProvider: ResourceProvider,
    private val delegate: DrugListViewModelMapper.Delegate
) {
    interface Delegate {
        fun onDrugSelectListener(item: DrugListItem)
    }

    enum class ViewId {
        DRUG_LIST_ITEM
    }
}