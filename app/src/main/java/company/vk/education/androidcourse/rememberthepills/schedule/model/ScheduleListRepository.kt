package company.vk.education.androidcourse.rememberthepills.schedule.model

import company.vk.education.androidcourse.rememberthepills.components.api.AnalyticService
import company.vk.education.androidcourse.rememberthepills.components.api.ApiModel.DauResponse
import company.vk.education.androidcourse.rememberthepills.components.api.ApiModel.ScheduleClickAnalyticRequest
import company.vk.education.androidcourse.rememberthepills.components.api.ApiModel.ScheduleClickAnalyticResponse
import company.vk.education.androidcourse.rememberthepills.components.storage.dao.CourseDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class ScheduleListRepository(
    private val courseDao: CourseDao,
    private val scheduleListRepositoryMapper: ScheduleListRepositoryMapper,
    private val analyticService: AnalyticService
) {
    fun scheduleList(selectedDate: Long): Flow<List<ScheduleListItem>> {
        val startDate = startDateInMilliseconds(selectedDate)
        val endDate = endDateInMilliseconds(selectedDate)
        return courseDao.courseCheckings(startDate, endDate).map {
            scheduleListRepositoryMapper.convertToScheduleListItem(it)
        }
    }

    private fun endDateInMilliseconds(selectedDate: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedDate
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.timeInMillis
    }

    private fun startDateInMilliseconds(selectedDate: Long): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedDate
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    suspend fun sendDauAnalytic(): DauResponse {
        return analyticService.sendDau()
    }

    suspend fun sendScheduleClickAnalytic(drugId: Long, courseId: Long, checked: Boolean): ScheduleClickAnalyticResponse {
        return analyticService.sendScheduleClick(ScheduleClickAnalyticRequest(drugId, courseId, checked))
    }
}