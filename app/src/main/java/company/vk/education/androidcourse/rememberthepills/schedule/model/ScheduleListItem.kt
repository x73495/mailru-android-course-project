package company.vk.education.androidcourse.rememberthepills.schedule.model

import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem

data class ScheduleListItem(
    val courseId: Long,
    val drugId: Long,
    val drugName: String,
    val quantity: Int,
    val drugMeasurementType: MeasurementItem,
    val checked: Boolean,
    val missed: Boolean,
    val time: Long
)