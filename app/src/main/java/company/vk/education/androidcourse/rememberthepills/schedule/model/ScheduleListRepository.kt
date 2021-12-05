package company.vk.education.androidcourse.rememberthepills.schedule.model

import company.vk.education.androidcourse.rememberthepills.components.storage.dao.CourseDao

class ScheduleListRepository(
    private val courseDao: CourseDao,
    private val scheduleListRepositoryMapper: ScheduleListRepositoryMapper
) {
}