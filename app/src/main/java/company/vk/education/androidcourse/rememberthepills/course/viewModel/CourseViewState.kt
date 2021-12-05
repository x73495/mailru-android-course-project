package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

data class CourseViewState(val drugId: Long,
                           val courseId: Long,
                           val drugName: String,
                           val drugType: String,
                           var quantity: Int?,
                           var foodAddictionItems: Array<out TextedItem>,
                           var selectedFoodAddictionItem: TextedItem,
                           var startedDateInMilliseconds: Long?,
                           var endedDateInMilliseconds: Long?,
                           var frequencyInDays: Int?,
                           var intakeTimesInMinutes: MutableList<Int>,
                           val screenMode: FormScreenMode)