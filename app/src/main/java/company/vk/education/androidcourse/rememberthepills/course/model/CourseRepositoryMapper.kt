package company.vk.education.androidcourse.rememberthepills.course.model

import company.vk.education.androidcourse.rememberthepills.components.mapper.DrugModelMapper
import company.vk.education.androidcourse.rememberthepills.components.models.FoodAddictionItem
import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.CourseCheckingEntity
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.CourseEntity
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.IntakeTimeEntity

class CourseRepositoryMapper(val drugModelMapper: DrugModelMapper) {

    fun convertToCourse(courseEntity: CourseEntity): Course {
        val foodAddictionType =
            FoodAddictionItem.values().find { it.id == courseEntity.foodAddictionTypeId }
                ?: FoodAddictionItem.NotDepend
        return Course(
            id = courseEntity.id,
            drugId = courseEntity.drugId,
            quantity = courseEntity.quantity,
            foodAddictionType = foodAddictionType,
            startingDateInMilliseconds = courseEntity.startingDateInMilliseconds,
            endingDateInMilliseconds = courseEntity.endingDateInMilliseconds,
            frequency = courseEntity.frequency
        )
    }

    fun convertToCourseIntakeTime(courseId: Long, intakeTimeEntity: IntakeTimeEntity): CourseIntakeTime {
        return CourseIntakeTime(
            id = intakeTimeEntity.id,
            courseId = intakeTimeEntity.courseId,
            timeInMilliseconds = intakeTimeEntity.timeInMilliseconds
        )
    }

    fun convertToCourseEntity(course: Course): CourseEntity {
        return CourseEntity(
            id = course.id,
            drugId = course.drugId,
            quantity = course.quantity,
            foodAddictionTypeId = course.foodAddictionType.id,
            frequency = course.frequency,
            startingDateInMilliseconds = course.startingDateInMilliseconds,
            endingDateInMilliseconds = course.endingDateInMilliseconds
        )
    }

    fun convertToIntakeTimeEntity(courseId: Long,
                                  intakeTime: CourseIntakeTime): IntakeTimeEntity {
        return IntakeTimeEntity(
            id = intakeTime.id,
            courseId = courseId,
            timeInMilliseconds = intakeTime.timeInMilliseconds
        )
    }

    fun createCheckingEntities(course: Course, intakeTimes: List<CourseIntakeTime>): List<CourseCheckingEntity> {
        val frequency = course.frequency
        val oneDayInMilliseconds = 86400000L
        val millisecondsFrequency = frequency * oneDayInMilliseconds
        val startDate = course.startingDateInMilliseconds
        val endDate = course.endingDateInMilliseconds
        val courseCheckings: MutableList<CourseCheckingEntity> = mutableListOf()
        for (date in startDate..endDate step millisecondsFrequency) {
            for (time in intakeTimes) {
                val courseChecking = CourseCheckingEntity(
                    courseId = course.id,
                    intakeTimeId = time.id,
                    date = date + time.timeInMilliseconds,
                    checked = false
                )
                courseCheckings.add(courseChecking)
            }
        }
        return courseCheckings
    }
}