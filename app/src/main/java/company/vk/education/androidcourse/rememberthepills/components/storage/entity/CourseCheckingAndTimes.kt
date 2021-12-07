package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CourseCheckingAndTimes(
    @Embedded
    val courseChecking: CourseCheckingEntity,
    @Relation(
        entity = CourseEntity::class,
        parentColumn = "course_id",
        entityColumn = "id"
    )
    val course: CourseEntity,
    @Relation(
        entity = CourseEntity::class,
        parentColumn = "intake_time_id",
        entityColumn = "id"
    )
    val intakeTime: IntakeTimeEntity
)