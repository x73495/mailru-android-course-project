package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "measurement_type") val measurementType: Int,
    val quantity: Int,
    @ColumnInfo(name = "food_addiction_type") val foodAddictionType: Int,
    @ColumnInfo(name = "starting_date_timestamp") val startingDateTimestamp: Long,
    @ColumnInfo(name = "ending_date_timestamp") val endingDateTimestamp: Long,
    val frequency: Int
)