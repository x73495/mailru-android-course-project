package company.vk.education.androidcourse.rememberthepills.schedule.model

import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.CourseCheckingAndTimes
import java.util.*

class ScheduleListRepositoryMapper {

    fun convertToScheduleListItem(courseCheckings: List<CourseCheckingAndTimes>): List<ScheduleListItem> {
        return courseCheckings.map { courseChecking ->
            val measurementType =
                MeasurementItem.values().find { courseChecking.course.drug.measurementTypeId == it.id }
                    ?: MeasurementItem.Tablespoons
            ScheduleListItem(
                courseId = courseChecking.course.course.id,
                drugId = courseChecking.course.drug.id,
                drugName = courseChecking.course.drug.name,
                quantity = courseChecking.course.course.quantity,
                drugMeasurementType = measurementType,
                checked = courseChecking.courseChecking.checked,
                missed = courseChecking.courseChecking.date < Date().time,
                time = courseChecking.courseChecking.date
            )
        }
    }
}