package company.vk.education.androidcourse.rememberthepills.fragments.schedule

import company.vk.education.androidcourse.rememberthepills.models.intakeTime.IntakeTime

data class CourseEntry(
    val nameOfDrug: String,
    val amountOfDrug: Int,
    var isDone: Boolean,
    var isMissed: Boolean,
    val idOfCourse: Int,
    val measurementOfDrug: String,
    val intakeTime: IntakeTime,
    val isCheckBoxShown: Boolean,
    val foodDependency: String
)