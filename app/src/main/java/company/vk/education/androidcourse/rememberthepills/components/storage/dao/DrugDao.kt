package company.vk.education.androidcourse.rememberthepills.components.storage.dao

import androidx.room.*
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.DrugEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrugDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drug: DrugEntity)

    @Update
    suspend fun update(drug: DrugEntity)

    @Query("SELECT * FROM drugs")
    fun getAllDrugs(): Flow<List<DrugEntity>>
}