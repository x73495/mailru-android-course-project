package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting

class CourseRoutingModel {
    class None: BaseRouting()
    class StartScheduleDestination(): BaseRouting()

    companion object {
        val none = CourseRoutingModel.None()
        val startScheduleDestination = CourseRoutingModel.StartScheduleDestination()
    }
}