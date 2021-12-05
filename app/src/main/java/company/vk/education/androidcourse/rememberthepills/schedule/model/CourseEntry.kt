package company.vk.education.androidcourse.rememberthepills.schedule.model

data class CourseEntry(
    val courseId: Long,
    val drugId: Long,
    val nameOfDrug: String,
    val amountOfDrug: Int,
    val isDone: Boolean,
    val isMissed: Boolean,
    val measurementOfDrug: String
)