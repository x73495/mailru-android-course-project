package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "course_checkings",
    foreignKeys = [
        ForeignKey(
            entity = CourseEntity::class,
            parentColumns = ["id"],
            childColumns = ["course_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = IntakeTimeEntity::class,
            parentColumns = ["id"],
            childColumns = ["intake_time_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CourseCheckingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "intake_time_id") val intakeTimeId: Long,
    @ColumnInfo(name = "course_id") val courseId: Long,
    val date: Long,
    val checked: Boolean
)
