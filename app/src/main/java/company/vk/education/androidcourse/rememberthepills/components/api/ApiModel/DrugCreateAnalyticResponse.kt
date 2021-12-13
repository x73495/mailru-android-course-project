package company.vk.education.androidcourse.rememberthepills.components.api.ApiModel

import com.squareup.moshi.Json

data class DrugCreateAnalyticResponse(
    @Json(name = "drug_id") val drugId: Long,
    val message: String,
    @Json(name = "event_date") val eventDate: String
)