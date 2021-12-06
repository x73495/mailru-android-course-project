package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem
import company.vk.education.androidcourse.rememberthepills.course.model.CourseIntakeTime

data class CourseViewState(val drugId: Long,
                           val courseId: Long,
                           var drugName: String,
                           var drugType: String,
                           var quantity: Int?,
                           var foodAddictionItems: Array<out TextedItem>,
                           var selectedFoodAddictionItem: TextedItem,
                           var startedDateInMilliseconds: Long?,
                           var endedDateInMilliseconds: Long?,
                           var frequencyInDays: Int?,
                           var intakeTimesInMilliseconds: MutableList<CourseIntakeTime>,
                           val screenMode: FormScreenMode)