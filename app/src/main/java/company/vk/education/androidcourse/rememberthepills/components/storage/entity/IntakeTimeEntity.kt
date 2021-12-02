package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intake_times")
data class IntakeTimeEntity(
    @PrimaryKey val id: Long,
    val time: Long
)