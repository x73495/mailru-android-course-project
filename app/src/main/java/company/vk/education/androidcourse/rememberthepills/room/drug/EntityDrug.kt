package company.vk.education.androidcourse.rememberthepills.room.drug

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityDrug(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "type") var type: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

// TODO: fix literals
