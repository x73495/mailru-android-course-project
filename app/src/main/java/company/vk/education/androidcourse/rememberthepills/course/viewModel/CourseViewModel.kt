package company.vk.education.androidcourse.rememberthepills.course.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.FoodAddictionItem
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem
import company.vk.education.androidcourse.rememberthepills.course.model.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel(
    private val mode: FormScreenMode,
    private val courseId: Long,
    private val drugId: Long,
    private val courseIntakeTimeFormatter: CourseIntakeTimeFormatter,
    private val resourceProvider: ResourceProvider,
    private val courseRepository: CourseRepository
) : ViewModel(), CourseViewModelMapper.Delegate {

    private val mapper = CourseViewModelMapper(resourceProvider, courseIntakeTimeFormatter, this)
    private var viewState: CourseViewState = CourseViewState(
        courseId = courseId,
        drugId = drugId,
        drugName = "",
        drugType = "",
        quantity = null,
        foodAddictionItems = FoodAddictionItem.values(),
        selectedFoodAddictionItem = FoodAddictionItem.values().first(),
        startedDateInMilliseconds = null,
        endedDateInMilliseconds = null,
        frequencyInDays = null,
        intakeTimesInMinutes = mutableListOf(),
        screenMode = mode
    )

    val presentationModel: MutableLiveData<CoursePresentationModel> by lazy {
        MutableLiveData<CoursePresentationModel>()
    }

    init {
        viewModelScope.launch {
            val drug = courseRepository.drugById(viewState.drugId)
            val type =
                resourceProvider.getString(drug.drugType.textId) + " / " + resourceProvider.getString(
                    drug.measurementType.textId
                )
            viewState.drugName = drug.name
            viewState.drugType = type
            updateDataUI()
        }
    }

    private fun updateDataUI() {
        val presentationModel = mapper.createDataPresentationModel(viewState)
        this.presentationModel.value = presentationModel
    }

    // Mapper handlers

    override fun onFoodAddictionTypeSelectListener(item: TextedItem) {
        viewState.selectedFoodAddictionItem = item
        updateDataUI()
    }

    override fun onFrequencyInDaysChangeListener(frequencyInDays: Int?) {
        viewState.frequencyInDays = frequencyInDays
        updateDataUI()
    }

    override fun onQuantityChangeListener(quantity: Int?) {
        viewState.quantity = quantity
        updateDataUI()
    }

    override fun onStartedDateSelectListener() {
        val startedDialogPresentationModel = mapper.createStartedDateDialogPresentationModel(viewState)
        presentationModel.value = startedDialogPresentationModel
    }

    override fun onEndedDateSelectListener() {
        val endedDialogPresentationModel = mapper.createEndedDateDialogPresentationModel(viewState)
        presentationModel.value = endedDialogPresentationModel
    }

    override fun onIntakeTimeAddListener() {
        val timeDialogPresentationModel = mapper.createTimeDialogPresentationModel(viewState)
        presentationModel.value = timeDialogPresentationModel
    }

    override fun onIntakeTimeRemoveListener(position: Int) {
        viewState.intakeTimesInMinutes.removeAt(position)
        updateDataUI()
    }

    // Fragment handlers

    fun selectedStartedDate(date: Long?) {
        viewState.startedDateInMilliseconds = date
        updateDataUI()
    }

    fun selectedEndedDate(date: Long?) {
        viewState.endedDateInMilliseconds = date
        updateDataUI()
    }

    fun cancelledInputDate() {
        updateDataUI()
    }

    fun selectedTime(hours: Int, minutes: Int) {
        val newTime = courseIntakeTimeFormatter.timeInMinutes(hours, minutes)
        viewState.intakeTimesInMinutes.add(newTime)
        viewState.intakeTimesInMinutes.sort()
        updateDataUI()
    }

    fun cancelledInputTime() {
        updateDataUI()
    }

    fun saveCourse() {

    }
}

class CourseViewModelFactory(
    private val mode: FormScreenMode,
    private val courseId: Long,
    private val drugId: Long,
    private val courseIntakeTimeFormatter: CourseIntakeTimeFormatter,
    private val resourceProvider: ResourceProvider,
    private val courseRepository: CourseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CourseViewModel(
            mode,
            courseId,
            drugId,
            courseIntakeTimeFormatter,
            resourceProvider,
            courseRepository
        ) as T
    }
}