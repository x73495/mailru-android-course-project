package company.vk.education.androidcourse.rememberthepills.room.course

import androidx.room.*

@Dao
interface DaoCourse {
    @Query("SELECT * FROM EntityCourse")
    fun getAll(): List<EntityCourse>

    @Query("SELECT * FROM EntityCourse WHERE id IN (:entityCourseIDs)")
    fun loadAllByIds(entityCourseIDs: IntArray): List<EntityCourse>

    @Query("SELECT * FROM EntityCourse WHERE id LIKE :id")
    fun findByID(id: Int): EntityCourse

    @Query("SELECT * FROM EntityCourse WHERE drug_id LIKE :drugID")
    fun findAllByDrugID(drugID: Int): List<EntityCourse>

    @Update
    fun update(entityCourse: EntityCourse)

    @Insert
    fun insertAll(vararg entitiesCourse: EntityCourse)

    @Delete
    fun delete(entityCourse: EntityCourse)

    @Delete
    fun deleteAll(entityCourseList: List<EntityCourse>)
}
