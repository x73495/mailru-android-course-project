package company.vk.education.androidcourse.rememberthepills.schedule.model

data class CourseEntry(
    val courseId: Int,
    val drugId: Int,
    val nameOfDrug: String,
    val amountOfDrug: Int,
    val isDone: Boolean,
    val isMissed: Boolean,
    val measurementOfDrug: String
)