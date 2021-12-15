package company.vk.education.androidcourse.rememberthepills.analytics.models.drug

import com.squareup.moshi.Json

data class DrugAdditionResponse(
    @field:Json(name = "message") val message: String,
    @field:Json(name = "timestamp") val timestamp: String
)
