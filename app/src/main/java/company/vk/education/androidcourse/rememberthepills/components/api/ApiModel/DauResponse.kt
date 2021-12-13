package company.vk.education.androidcourse.rememberthepills.components.api.ApiModel

import com.squareup.moshi.Json

data class DauResponse(
    val message: String,
    @Json(name = "event_date") val eventDate: String
)