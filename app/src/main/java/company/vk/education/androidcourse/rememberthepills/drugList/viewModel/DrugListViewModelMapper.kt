package company.vk.education.androidcourse.rememberthepills.drugList.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewState
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListItem
import company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.item.DrugListDataItem

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

    fun createPresentationModel(viewState: DrugListViewState): DrugListPresentationModel {
        return DrugListPresentationModel(
            listItems = createDataItems(viewState)
        )
    }

    private fun createDataItems(viewState: DrugListViewState): List<BaseDataItem> {
        return viewState.drugs.map {
            val type =
                resourceProvider.getString(it.drugType.textId) + " / " + resourceProvider.getString(
                    it.measurementType.textId
                )
            DrugListDataItem(
                id = ViewId.DRUG_LIST_ITEM.name + "$it.id",
                name = it.name,
                type = type,
                selectionHandler = {
                    delegate.onDrugSelectListener(it)
                }
            )
        }
    }
}