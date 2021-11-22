package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.form.model.AutocomplitedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.TextedTextFieldDataItem

class DrugViewModelMapper(private val resourceProvider: ResourceProvider) {
    enum class ViewId {
        DRUG_NAME,
        DRUG_TYPE
    }

    fun createDataItems(viewState: DrugViewState): List<BaseDataItem> {
        val drugNameItem = TextedTextFieldDataItem(
            id = ViewId.DRUG_NAME.ordinal,
            text = viewState.drugNameText,
            hint = resourceProvider.getString(R.string.drug_name)
        )

        val drugTypesItem = AutocomplitedTextFieldDataItem(
            id = ViewId.DRUG_TYPE.ordinal,
            textedItems = viewState.drugItems,
            selectedTextedItem = viewState.selectedTypeDrugItem,
            hint = resourceProvider.getString(R.string.drug_type),
            itemSelectedHandler = { newDrugTypeItem ->

            }
        )

        return listOf(drugNameItem, drugTypesItem)
    }
}