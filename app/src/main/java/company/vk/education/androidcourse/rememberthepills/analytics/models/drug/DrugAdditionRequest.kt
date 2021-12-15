package company.vk.education.androidcourse.rememberthepills.analytics.models.drug

import com.squareup.moshi.Json

data class DrugAdditionRequest (
    @field:Json(name = "name") val name: String,
    @field:Json(name = "type") val type: String
)
