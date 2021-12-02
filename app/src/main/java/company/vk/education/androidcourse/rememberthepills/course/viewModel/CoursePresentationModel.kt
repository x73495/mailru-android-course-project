package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem

sealed class CoursePresentationModel {

}

class CourseDataPresentationModel(
    val listItems: List<BaseDataItem>,
    val applyButtonTitle: String,
    val isRemoveButtonHidden: Boolean
): CoursePresentationModel()

class CourseDateDialogPresentationModel(
    val dateType: DateType,
    val selectedDateInMilliseconds: Long?,
    val title: String
) : CoursePresentationModel() {
    enum class DateType {
        STARTED, ENDED
    }
}

class CourseTimeDialogPresentationModel(
    private val selectedHours: Int?,
    private val selectedMinutes: Int?,
    val title: String
) : CoursePresentationModel() {
    fun getHours() = selectedHours ?: 0
    fun getMinutes() = selectedMinutes ?: 0
}
