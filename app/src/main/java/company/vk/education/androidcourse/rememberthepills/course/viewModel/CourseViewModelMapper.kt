package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.form.model.AutocomplitedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.NumberedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.SectionHeaderDataItem
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

class CourseViewModelMapper(
    private val resourceProvider: ResourceProvider,
    private val delegate: CourseViewModelMapper.Delegate
) {
    interface Delegate {
        fun onMeasurementTypeSelectListener(item: TextedItem)
        fun onFoodAddictionTypeSelectListener(item: TextedItem)
        fun onQuantityChangeListener(quantity: Int?)
    }

    enum class ViewId {
        DRUG_NAME_SECTION_HEADER,
        DOSAGE_SECTION_HEADER,
        MEASUREMENT_TYPE,
        AMOUNT_TYPE,
        FOOD_ADDICTION_TYPE
    }

    fun createPresentationModel(viewState: CourseViewState): CoursePresentationModel {
        return CoursePresentationModel(createDataItems(viewState))
    }

    private fun createDataItems(viewState: CourseViewState): List<BaseDataItem> {
        val drugNameSectionHeader = SectionHeaderDataItem(
            id = ViewId.DRUG_NAME_SECTION_HEADER.ordinal,
            text = resourceProvider.getString(R.string.drug)
        )
        val dosageSectionHeader = SectionHeaderDataItem(
            id = ViewId.DOSAGE_SECTION_HEADER.ordinal,
            text = resourceProvider.getString(R.string.dosage)
        )
        val measurementTypesItem = AutocomplitedTextFieldDataItem(
            id = ViewId.MEASUREMENT_TYPE.ordinal,
            textedItems = viewState.measurementItems,
            selectedTextedItem = viewState.selectedMeasurementItem,
            hint = resourceProvider.getString(R.string.measurement),
            selectedItemHandler = { newMeasurementTypeItem ->
                delegate.onMeasurementTypeSelectListener(newMeasurementTypeItem)
            }
        )
        val amountItem = NumberedTextFieldDataItem(
            id = ViewId.AMOUNT_TYPE.ordinal,
            number = viewState.quantity,
            hint = resourceProvider.getString(R.string.quantity),
            maxLength = resourceProvider.getInteger(R.integer.numbered_text_input_max_length),
            editingNumberHandler = {
                delegate.onQuantityChangeListener(it)
            }
        )
        val foodAddictionTypesItem = AutocomplitedTextFieldDataItem(
            id = ViewId.FOOD_ADDICTION_TYPE.ordinal,
            textedItems = viewState.foodAddictionItems,
            selectedTextedItem = viewState.selectedFoodAddictionItem,
            hint = resourceProvider.getString(R.string.food_addiction),
            selectedItemHandler = { newFoodAddictionTypeItem ->
                delegate.onFoodAddictionTypeSelectListener(newFoodAddictionTypeItem)
            }
        )
        return listOf(
            drugNameSectionHeader,
            dosageSectionHeader,
            measurementTypesItem,
            amountItem,
            foodAddictionTypesItem
        )
    }
}