package company.vk.education.androidcourse.rememberthepills.room.entryForToday

import androidx.room.*
import company.vk.education.androidcourse.rememberthepills.models.intakeTime.IntakeTime

@Dao
interface DaoEntryForToday {
    @Query("SELECT * FROM EntityEntryForToday")
    fun getAll(): List<EntityEntryForToday>

    @Query("SELECT * FROM EntityEntryForToday WHERE intake_time LIKE :intakeTime and related_course_id LIKE :relatedCourseID")
    fun findByIntakeTime(intakeTime: IntakeTime, relatedCourseID: Int): EntityEntryForToday

    @Update
    fun update(entityEntryForToday: EntityEntryForToday)

    @Insert
    fun insertAll(vararg entitiesEntryForToday: EntityEntryForToday)

    @Delete
    fun delete(entityEntryForToday: EntityEntryForToday)

    @Query("DELETE FROM EntityEntryForToday")
    fun deleteEverything()
}
