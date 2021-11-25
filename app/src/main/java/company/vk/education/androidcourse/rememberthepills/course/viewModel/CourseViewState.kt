package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

data class CourseViewState(val drugId: Int?,
                           val courseId: Int?,
                           var measurementItems: Array<out TextedItem>,
                           var selectedMeasurementItem: TextedItem,
                           var foodAddictionItems: Array<out TextedItem>,
                           var selectedFoodAddictionItem: TextedItem,
                           val screenMode: FormScreenMode)