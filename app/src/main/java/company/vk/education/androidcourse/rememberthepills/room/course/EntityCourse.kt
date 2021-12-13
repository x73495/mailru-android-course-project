package company.vk.education.androidcourse.rememberthepills.room.course

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import company.vk.education.androidcourse.rememberthepills.models.intakeTime.IntakeTime
import java.time.LocalDate

@Entity
data class EntityCourse(
    @ColumnInfo(name = "drug_id") var drugID: Int,
    @ColumnInfo(name = "measurement") var measurement: String,
    @ColumnInfo(name = "amount") var amount: Int,
    @ColumnInfo(name = "food_dependency") var foodDependency: String,
    @ColumnInfo(name = "date_start") var dateStart: LocalDate,
    @ColumnInfo(name = "date_end") var dateEnd: LocalDate,
    @ColumnInfo(name = "frequency_in_days") var frequencyInDays: Int,
    @ColumnInfo(name = "intake_times") var intakeTimes: List<IntakeTime>,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)

// TODO: fix literals
