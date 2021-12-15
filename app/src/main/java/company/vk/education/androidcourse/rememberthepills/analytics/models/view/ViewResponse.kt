package company.vk.education.androidcourse.rememberthepills.analytics.models.view

import com.squareup.moshi.Json

data class ViewResponse(
    @field:Json(name = "message") val message: String,
    @field:Json(name = "timestamp") val timestamp: String
)