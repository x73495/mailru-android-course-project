package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "drug_id") val drugId: Long,
    val quantity: Int,
    @ColumnInfo(name = "food_addiction_type") val foodAddictionTypeId: Int,
    @ColumnInfo(name = "starting_date_timestamp") val startingDateTimestamp: Long,
    @ColumnInfo(name = "ending_date_timestamp") val endingDateTimestamp: Long,
    val frequency: Int
)