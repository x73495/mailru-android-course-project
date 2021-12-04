package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.form.model.AutocomplitedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.TextedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem
import company.vk.education.androidcourse.rememberthepills.drug.model.Drug

class DrugViewModelMapper(
    private val resourceProvider: ResourceProvider,
    private val delegate: DrugViewModelMapper.Delegate
) {
    interface Delegate {
        fun onDrugTypeSelectListener(item: TextedItem)
        fun onDrugNameChangeListener(text: String?)
        fun onMeasurementTypeSelectListener(item: TextedItem)
    }

    enum class ViewId {
        DRUG_NAME,
        DRUG_TYPE,
        DRUG_MEASUREMENT_TYPE
    }

    fun createModel(viewState: DrugViewState): Drug {
        return Drug(
            viewState.drugId,
            viewState.drugNameText ?: "",
            viewState.selectedMeasurementItem,
            viewState.selectedDrugTypeItem
        )
    }

    fun createPresentationModel(viewState: DrugViewState): DrugPresentationModel {
        return DrugPresentationModel(
            listItems = createDataItems(viewState),
            applyButtonTitle = getApplyButtonTitle(viewState),
            isRemoveButtonHidden = getRemoveButtonHidden(viewState)
        )
    }

    private fun createDataItems(viewState: DrugViewState): List<BaseDataItem> {
        val drugNameItem = TextedTextFieldDataItem(
            id = ViewId.DRUG_NAME.name,
            text = viewState.drugNameText,
            hint = resourceProvider.getString(R.string.drug_name),
            editingTextHandler = {
                delegate.onDrugNameChangeListener(it)
            }
        )

        val drugTypesItem = AutocomplitedTextFieldDataItem(
            id = ViewId.DRUG_TYPE.name,
            textedItems = viewState.drugItems,
            selectedTextedItem = viewState.selectedDrugTypeItem,
            hint = resourceProvider.getString(R.string.drug_type),
            selectedItemHandler = { newDrugTypeItem ->
                delegate.onDrugTypeSelectListener(newDrugTypeItem)
            }
        )

        val drugMeasurementTypeItem = AutocomplitedTextFieldDataItem(
            id = ViewId.DRUG_MEASUREMENT_TYPE.name,
            textedItems = viewState.measurementItems,
            selectedTextedItem = viewState.selectedMeasurementItem,
            hint = resourceProvider.getString(R.string.measurement),
            selectedItemHandler = { newMeasurementTypeItem ->
                delegate.onMeasurementTypeSelectListener(newMeasurementTypeItem)
            }
        )

        return listOf(drugNameItem, drugTypesItem, drugMeasurementTypeItem)
    }

    private fun getApplyButtonTitle(viewState: DrugViewState): String {
        return when(viewState.screenMode) {
            FormScreenMode.EDITING -> resourceProvider.getString(R.string.save)
            FormScreenMode.CREATING -> resourceProvider.getString(R.string.add)
        }
    }

    private fun getRemoveButtonHidden(viewState: DrugViewState): Boolean {
        return when(viewState.screenMode) {
            FormScreenMode.EDITING -> false
            FormScreenMode.CREATING -> true
        }
    }
}