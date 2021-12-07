package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "course_checkings",
)
data class CourseCheckingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "intake_time_id") val intakeTimeId: Long,
    @ColumnInfo(name = "course_id") val courseId: Long,
    val date: Long,
    val checked: Boolean
)
