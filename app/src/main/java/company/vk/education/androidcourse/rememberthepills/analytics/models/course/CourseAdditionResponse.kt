package company.vk.education.androidcourse.rememberthepills.analytics.models.course

import com.squareup.moshi.Json

data class CourseAdditionResponse(
    @field:Json(name = "message") val message: String,
    @field:Json(name = "timestamp") val timestamp: String
)