package company.vk.education.androidcourse.rememberthepills.course.model

import company.vk.education.androidcourse.rememberthepills.components.storage.dao.CourseDao

class CourseRepository(
    private val courseDao: CourseDao,
    private val mapper: CourseRepositoryMapper
) {
}