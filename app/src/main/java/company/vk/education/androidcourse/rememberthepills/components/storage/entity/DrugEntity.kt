package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class DrugEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "drug_type") val drugTypeId: Int,
    @ColumnInfo(name = "measurement_type") val measurementTypeId: Int
)