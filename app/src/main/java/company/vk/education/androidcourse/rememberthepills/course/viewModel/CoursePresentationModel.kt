package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem

sealed class CoursePresentationModel {

}

class CourseDataPresentationModel(
    val listItems: List<BaseDataItem>
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
    val selectedTimeInMinutes: Int?,
    val title: String
) : CoursePresentationModel() {
    fun getHours() = if (selectedTimeInMinutes != null) selectedTimeInMinutes / 60 else 0
    fun getMinutes() = if (selectedTimeInMinutes != null) selectedTimeInMinutes % 60 else 0
}
