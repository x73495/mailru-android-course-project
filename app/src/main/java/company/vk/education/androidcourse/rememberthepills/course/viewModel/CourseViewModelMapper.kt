package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.form.model.AutocomplitedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.DatedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.NumberedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.SectionHeaderDataItem
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.AddIntakeTimeDataItem
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.CourseDrugTitleDataItem
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.IntakeTimeDataItem
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewState
import java.text.SimpleDateFormat

class CourseViewModelMapper(
    private val resourceProvider: ResourceProvider,
    private val courseIntakeTimeFormatter: CourseIntakeTimeFormatter,
    private val delegate: CourseViewModelMapper.Delegate
) {
    interface Delegate {
        fun onMeasurementTypeSelectListener(item: TextedItem)
        fun onFoodAddictionTypeSelectListener(item: TextedItem)
        fun onQuantityChangeListener(quantity: Int?)
        fun onFrequencyInDaysChangeListener(frequencyInDays: Int?)
        fun onStartedDateSelectListener()
        fun onEndedDateSelectListener()
        fun onIntakeTimeAddListener()
        fun onIntakeTimeRemoveListener(position: Int)
    }

    enum class ViewId {
        DRUG_NAME_SECTION_HEADER,
        DRUG_TITLE,
        DOSAGE_SECTION_HEADER,
        MEASUREMENT_TYPE,
        AMOUNT_TYPE,
        FOOD_ADDICTION_TYPE,
        MEDICATION_PERIOD_SECTION_HEADER,
        STARTED_DATE_MEDICATION_TYPE,
        ENDED_DATE_MEDICATION_TYPE,
        FREQUENCY_IN_DAYS_MEDICATION_TYPE,
        TIME_MEDICATION_SECTION_HEADER,
        INTAKE_TIME_TYPE,
        ADD_INTAKE_TIME_TYPE
    }

    fun createTimeDialogPresentationModel(viewState: CourseViewState): CourseTimeDialogPresentationModel {
        return CourseTimeDialogPresentationModel(
            selectedHours = null,
            selectedMinutes = null,
            title = resourceProvider.getString(R.string.pick_intake_time)
        )
    }

    fun createStartedDateDialogPresentationModel(viewState: CourseViewState): CourseDateDialogPresentationModel {
        return CourseDateDialogPresentationModel(
            dateType = CourseDateDialogPresentationModel.DateType.STARTED,
            selectedDateInMilliseconds = viewState.startedDateInMilliseconds,
            title = resourceProvider.getString(R.string.pick_course_start)
        )
    }

    fun createEndedDateDialogPresentationModel(viewState: CourseViewState): CourseDateDialogPresentationModel {
        return CourseDateDialogPresentationModel(
            dateType = CourseDateDialogPresentationModel.DateType.ENDED,
            selectedDateInMilliseconds = viewState.startedDateInMilliseconds,
            title = resourceProvider.getString(R.string.pick_course_end)
        )
    }

    fun createDataPresentationModel(viewState: CourseViewState): CourseDataPresentationModel {
        return CourseDataPresentationModel(
            listItems = createDataItems(viewState),
            applyButtonTitle = getApplyButtonTitle(viewState),
            isRemoveButtonHidden = getRemoveButtonHidden(viewState)
        )
    }

    private fun createDataItems(viewState: CourseViewState): List<BaseDataItem> {
        val drugNameSectionHeader = SectionHeaderDataItem(
            id = ViewId.DRUG_NAME_SECTION_HEADER.name,
            text = resourceProvider.getString(R.string.drug)
        )

        val drugTitleItem = CourseDrugTitleDataItem(
            id = ViewId.DRUG_TITLE.name,
            headline = viewState.drugName,
            description = viewState.drugType
        )

        val dosageSectionHeader = SectionHeaderDataItem(
            id = ViewId.DOSAGE_SECTION_HEADER.name,
            text = resourceProvider.getString(R.string.dosage)
        )
        val measurementTypesItem = AutocomplitedTextFieldDataItem(
            id = ViewId.MEASUREMENT_TYPE.name,
            textedItems = viewState.measurementItems,
            selectedTextedItem = viewState.selectedMeasurementItem,
            hint = resourceProvider.getString(R.string.measurement),
            selectedItemHandler = { newMeasurementTypeItem ->
                delegate.onMeasurementTypeSelectListener(newMeasurementTypeItem)
            }
        )
        val amountItem = NumberedTextFieldDataItem(
            id = ViewId.AMOUNT_TYPE.name,
            number = viewState.quantity,
            hint = resourceProvider.getString(R.string.quantity),
            maxLength = resourceProvider.getInteger(R.integer.numbered_text_input_max_length),
            editingNumberHandler = {
                delegate.onQuantityChangeListener(it)
            }
        )
        val foodAddictionTypesItem = AutocomplitedTextFieldDataItem(
            id = ViewId.FOOD_ADDICTION_TYPE.name,
            textedItems = viewState.foodAddictionItems,
            selectedTextedItem = viewState.selectedFoodAddictionItem,
            hint = resourceProvider.getString(R.string.food_addiction),
            selectedItemHandler = { newFoodAddictionTypeItem ->
                delegate.onFoodAddictionTypeSelectListener(newFoodAddictionTypeItem)
            }
        )
        val medicationPeriodSectionHeader = SectionHeaderDataItem(
            id = ViewId.MEDICATION_PERIOD_SECTION_HEADER.name,
            text = resourceProvider.getString(R.string.medication_period)
        )
        val startedDateItem = DatedTextFieldDataItem(
            id = ViewId.STARTED_DATE_MEDICATION_TYPE.name,
            hint = resourceProvider.getString(R.string.starting_medication),
            dateInMilliseconds = viewState.startedDateInMilliseconds,
            dateFormat = SimpleDateFormat("dd/MM/yyyy"),
            startedSelectDateHandler = {
                delegate.onStartedDateSelectListener()
            }
        )
        val endedDateItem = DatedTextFieldDataItem(
            id = ViewId.ENDED_DATE_MEDICATION_TYPE.name,
            hint = resourceProvider.getString(R.string.end_of_medication),
            dateInMilliseconds = viewState.endedDateInMilliseconds,
            dateFormat = SimpleDateFormat("dd/MM/yyyy"),
            startedSelectDateHandler = {
                delegate.onEndedDateSelectListener()
            }
        )
        val frequencyInDaysItem = NumberedTextFieldDataItem(
            id = ViewId.FREQUENCY_IN_DAYS_MEDICATION_TYPE.name,
            number = viewState.frequencyInDays,
            hint = resourceProvider.getString(R.string.frequency_in_days_medication),
            maxLength = resourceProvider.getInteger(R.integer.numbered_text_input_max_length),
            editingNumberHandler = {
                delegate.onFrequencyInDaysChangeListener(it)
            }
        )
        val timeMedicationSectionHeader = SectionHeaderDataItem(
            id = ViewId.TIME_MEDICATION_SECTION_HEADER.name,
            text = resourceProvider.getString(R.string.time_medication)
        )


        val intakeTimeItems = viewState.intakeTimesInMinutes.mapIndexed { index, timeInMinutes ->
            IntakeTimeDataItem(
                id = ViewId.INTAKE_TIME_TYPE.name + "$index",
                timeString = courseIntakeTimeFormatter.hoursAndMinutesString(timeInMinutes),
                removeTimeHandler = {
                    delegate.onIntakeTimeRemoveListener(index)
                }
            )
        }

        val addIntakeTimeItem = AddIntakeTimeDataItem(
            id = ViewId.ADD_INTAKE_TIME_TYPE.name,
            addIntakeTimeHandler = {
                delegate.onIntakeTimeAddListener()
            }
        )

        val resultList = mutableListOf(
            drugNameSectionHeader,
            drugTitleItem,
            dosageSectionHeader,
            measurementTypesItem,
            amountItem,
            foodAddictionTypesItem,
            medicationPeriodSectionHeader,
            startedDateItem,
            endedDateItem,
            frequencyInDaysItem,
            timeMedicationSectionHeader
        )
        resultList.addAll(intakeTimeItems)
        resultList.add(addIntakeTimeItem)
        return resultList
    }

    private fun getApplyButtonTitle(viewState: CourseViewState): String {
        return when(viewState.screenMode) {
            FormScreenMode.EDITING -> resourceProvider.getString(R.string.save)
            FormScreenMode.CREATING -> resourceProvider.getString(R.string.add)
        }
    }

    private fun getRemoveButtonHidden(viewState: CourseViewState): Boolean {
        return when(viewState.screenMode) {
            FormScreenMode.EDITING -> false
            FormScreenMode.CREATING -> true
        }
    }
}