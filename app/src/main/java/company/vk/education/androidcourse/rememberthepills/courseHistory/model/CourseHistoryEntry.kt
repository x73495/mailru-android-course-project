package company.vk.education.androidcourse.rememberthepills.courseHistory.model

import java.time.LocalDate

data class CourseHistoryEntry (
    val nameOfDrug: String,
    val amountOfDrug: Int,
    val measureOfDrug: String,
    val frequencyAmount: Int,
    val frequencyMeasure: String,
    val dateStart: LocalDate,
    val dateEnd: LocalDate
)