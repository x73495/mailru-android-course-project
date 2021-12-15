package company.vk.education.androidcourse.rememberthepills.analytics.models.course

import com.squareup.moshi.Json

data class CourseAdditionRequest (
    @field:Json(name = "drug_name") val drug_name: String,
    @field:Json(name = "measurement") val measurement: String,
    @field:Json(name = "amount") val amount: String,
    @field:Json(name = "food_dependency") val food_dependency: String,
    @field:Json(name = "date_start") val date_start: String,
    @field:Json(name = "date_end") val date_end: String,
    @field:Json(name = "frequency_in_days") val frequency_in_days: String,
    @field:Json(name = "intake_times") val intake_times: String
)
