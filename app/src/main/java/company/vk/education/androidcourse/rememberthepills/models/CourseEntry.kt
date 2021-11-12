package company.vk.education.androidcourse.rememberthepills.models

data class CourseEntry(
    val nameOfDrug: String,
    val amountOfDrug: Int,
    val isDone: Boolean,
    val isMissed: Boolean,
    val idOfCourse: Int
)