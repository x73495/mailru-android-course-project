package company.vk.education.androidcourse.rememberthepills.components.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class DrugEntity(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "drug_type") val drugType: Int
)