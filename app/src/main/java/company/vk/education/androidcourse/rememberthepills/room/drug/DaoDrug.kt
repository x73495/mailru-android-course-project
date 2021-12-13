package company.vk.education.androidcourse.rememberthepills.room.drug

import androidx.room.*

@Dao
interface DaoDrug {
    @Query("SELECT * FROM EntityDrug")
    fun getAll(): List<EntityDrug>

    @Query("SELECT * FROM EntityDrug WHERE id IN (:entityDrugIDs)")
    fun loadAllByIds(entityDrugIDs: IntArray): List<EntityDrug>

    @Query("SELECT * FROM EntityDrug WHERE name LIKE :name AND type LIKE :type LIMIT 1")
    fun findByName(name: String, type: String): EntityDrug

    @Query("SELECT * FROM EntityDrug WHERE id LIKE :id")
    fun findByID(id: Int): EntityDrug

    @Update
    fun update(entityDrug: EntityDrug)

    @Insert
    fun insertAll(vararg entitiesDrug: EntityDrug)

    @Delete
    fun delete(entityDrug: EntityDrug)
}
