package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CourseAndTimes(
    @Embedded
    val course: CourseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "course_id"
    )
    val intakeTimes: List<IntakeTimeEntity>
)