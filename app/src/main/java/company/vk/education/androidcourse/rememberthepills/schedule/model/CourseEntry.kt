package company.vk.education.androidcourse.rememberthepills.schedule.model

data class CourseEntry(
    val nameOfDrug: String,
    val amountOfDrug: Int,
    val isDone: Boolean,
    val isMissed: Boolean,
    val idOfCourse: Int,
    val measurementOfDrug: String
)