package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "course_date_time_checkings",
    primaryKeys = ["intake_time_id", "course_id", "date"]
)
data class CourseDateTimeChecking(
    @ColumnInfo(name = "intake_time_id") val intakeTimeId: Long,
    @ColumnInfo(name = "course_id") val courseId: Long,
    val date: Long,
    val checked: Boolean
)
