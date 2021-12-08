package company.vk.education.androidcourse.rememberthepills.course.model

import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

data class Course(
    val id: Long,
    val drugId: Long,
    val quantity: Int,
    val foodAddictionType: TextedItem,
    val startingDateInMilliseconds: Long,
    val endingDateInMilliseconds: Long,
    val frequency: Int
)