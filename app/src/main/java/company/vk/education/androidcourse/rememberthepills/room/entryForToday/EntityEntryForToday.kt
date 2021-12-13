package company.vk.education.androidcourse.rememberthepills.room.entryForToday

import androidx.room.ColumnInfo
import androidx.room.Entity
import company.vk.education.androidcourse.rememberthepills.models.intakeTime.IntakeTime

@Entity(primaryKeys = ["intake_time", "related_course_id"])
data class EntityEntryForToday(
    @ColumnInfo(name = "intake_time") val intakeTime: IntakeTime,
    @ColumnInfo(name = "related_course_id") val relatedCourseID: Int,
    @ColumnInfo(name = "is_done") var isDone: Boolean,
    @ColumnInfo(name = "is_missed") var isMissed: Boolean,
)
