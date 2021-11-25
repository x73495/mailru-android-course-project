package company.vk.education.androidcourse.rememberthepills.course.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.FoodAddictionItem
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

class CourseViewModel(
    private val mode: FormScreenMode,
    private val courseId: Int?,
    private val drugId: Int?,
    private val resourceProvider: ResourceProvider
) : ViewModel(), CourseViewModelMapper.Delegate {

    private val mapper = CourseViewModelMapper(resourceProvider, this)
    private var viewState: CourseViewState = CourseViewState(courseId = courseId,
        drugId = drugId,
        measurementItems = MeasurementItem.values(),
        selectedMeasurementItem = MeasurementItem.values().first(),
        foodAddictionItems = FoodAddictionItem.values(),
        selectedFoodAddictionItem = FoodAddictionItem.values().first(),
        screenMode = mode
    )

    val presentationModel: MutableLiveData<CoursePresentationModel> by lazy {
        MutableLiveData<CoursePresentationModel>()
    }

    init {
        updateUI()
    }

    private fun updateUI() {
        val presentationModel = mapper.createPresentationModel(viewState)
        this.presentationModel.value = presentationModel
    }

    // Mapper handlers

    override fun onMeasurementTypeSelectListener(item: TextedItem) {
        viewState.selectedMeasurementItem = item
        updateUI()
    }

    override fun onFoodAddictionTypeSelectListener(item: TextedItem) {
        viewState.selectedFoodAddictionItem = item
        updateUI()
    }
}

class CourseViewModelFactory(
    private val mode: FormScreenMode,
    private val courseId: Int?,
    private val drugId: Int?,
    private val resourceProvider: ResourceProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CourseViewModel(mode, courseId, drugId, resourceProvider) as T
    }
}