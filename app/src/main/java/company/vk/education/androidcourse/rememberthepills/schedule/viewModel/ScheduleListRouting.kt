package company.vk.education.androidcourse.rememberthepills.schedule.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting

class ScheduleListRouting {
    class None: BaseRouting()
    class DrugList: BaseRouting()
    class EditingCourse(val courseId: Long, val drugId: Long): BaseRouting()

    companion object {
        val none = None()
        val drugList = DrugList()

        fun courseEditing(courseId: Long, drugId: Long): BaseRouting {
            return EditingCourse(courseId, drugId)
        }
    }
}