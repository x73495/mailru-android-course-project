package company.vk.education.androidcourse.rememberthepills.components.storage.dao

import androidx.room.*
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class CourseDao {

    // TODO: доделать корректное удаление времени из курса

    @Query("Select * FROM course_checkings WHERE date BETWEEN :dateStart AND :dateEnd")
    @Transaction
    abstract fun courseCheckings(dateStart: Long, dateEnd: Long): Flow<List<CourseCheckingAndTimes>>

    @Query("Select * FROM courses WHERE id = :courseId")
    abstract fun courseAndTimes(courseId: Long): CourseAndTimes

    @Delete
    abstract fun deleteCourse(course: CourseEntity)

    @Insert
    abstract suspend fun insertCourse(course: CourseEntity): Long

    @Insert
    abstract suspend fun insertTimes(intakeTimes: List<IntakeTimeEntity>): List<Long>

    @Insert
    abstract suspend fun insertCourseCheckings(courseCheckings: List<CourseCheckingEntity>): List<Long>

    @Transaction
    open suspend fun insert(course: CourseEntity,
                            times: List<IntakeTimeEntity>,
                            courseCheckings: List<CourseCheckingEntity>) {
        val courseId = insertCourse(course)
        val intakeTimes = times.map { it.copy(courseId = courseId) }
        val timeIds = insertTimes(intakeTimes)
        val updatedCourseCheckings = courseCheckings.mapIndexed { index, item ->
            // очень сложный костыль, переделать
            item.copy(courseId = courseId, intakeTimeId = timeIds[index % timeIds.size])
        }
        insertCourseCheckings(updatedCourseCheckings)
    }
}