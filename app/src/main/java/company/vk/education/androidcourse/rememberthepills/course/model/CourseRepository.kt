package company.vk.education.androidcourse.rememberthepills.course.model

import company.vk.education.androidcourse.rememberthepills.components.storage.dao.CourseDao
import company.vk.education.androidcourse.rememberthepills.components.storage.dao.DrugDao
import company.vk.education.androidcourse.rememberthepills.components.models.Drug
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.CourseEntity
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.DrugEntity
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.IntakeTimeEntity
import java.util.*

class CourseRepository(
    private val courseDao: CourseDao,
    private val drugDao: DrugDao,
    private val mapper: CourseRepositoryMapper
) {

    suspend fun drugById(id: Long): Drug {
        return mapper.drugModelMapper.convertToDrug(drugDao.drugById(id))
    }

//    suspend fun courseAndTimesById(courseId: Long): Pair<Course, List<CourseIntakeTime>> {
//
//    }

    suspend fun createCourse(course: Course, intakeTimes: List<CourseIntakeTime>) {
        val courseEntity = mapper.convertToCourseEntity(course)
        val intakeTimeEntities = intakeTimes.map {
            mapper.convertToIntakeTimeEntity(
                courseId = course.id,
                intakeTime = it
            )
        }
        val courseCheckings = mapper.createCheckingEntities(course, intakeTimes)
        courseDao.insert(courseEntity, intakeTimeEntities, courseCheckings)
    }

    suspend fun deleteCourseById(id: Long) {
        courseDao.deleteCourseById(id)
    }

    suspend fun updateCourse(course: Course, intakeTimes: List<CourseIntakeTime>) {
        // TODO: обновление доделать
    }
}