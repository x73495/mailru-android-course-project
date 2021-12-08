package company.vk.education.androidcourse.rememberthepills.course.model

data class CourseIntakeTime(
    val id: Long = 0,
    val courseId: Long,
    val timeInMilliseconds: Long
)