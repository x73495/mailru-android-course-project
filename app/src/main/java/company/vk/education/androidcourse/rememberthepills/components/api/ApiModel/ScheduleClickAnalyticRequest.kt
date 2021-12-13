package company.vk.education.androidcourse.rememberthepills.components.api.ApiModel

import com.squareup.moshi.Json

data class ScheduleClickAnalyticRequest(
    @Json(name = "drug_id") val drugId: Long,
    @Json(name = "course_id") val courseId: Long,
    val checked: Boolean
)