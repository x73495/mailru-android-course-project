package company.vk.education.androidcourse.rememberthepills.components.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.CourseCheckingEntity
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.CourseEntity
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.IntakeTimeEntity

@Dao
abstract class CourseDao {

    @Insert
    abstract suspend fun insertCourse(course: CourseEntity): Long

    @Insert
    abstract suspend fun insertTimes(intakeTimes: List<IntakeTimeEntity>): List<Long>

    @Insert
    abstract suspend fun insertCheckings(courseCheckings: List<CourseCheckingEntity>): List<Long>

    @Transaction
    open suspend fun insert(course: CourseEntity,
                            times: List<IntakeTimeEntity>,
                            courseCheckings: List<CourseCheckingEntity>) {
        val courseId = insertCourse(course)
        val intakeTimes = times.map { it.copy(courseId = courseId) }
        val timeIds = insertTimes(intakeTimes)
        val updatedCourseCheckings = courseCheckings.mapIndexed { index, item ->
            item.copy(courseId = courseId, intakeTimeId = timeIds[index % timeIds.size])
        }
        insertCheckings(updatedCourseCheckings)
    }
}