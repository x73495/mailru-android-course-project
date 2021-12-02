package company.vk.education.androidcourse.rememberthepills.course.view.adapter

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseViewType

class CourseViewType {
    class IntakeTime: BaseViewType()
    class AddIntakeTime: BaseViewType()
    class CourseDrugTitle: BaseViewType()

    companion object {
        val intakeTime = IntakeTime()
        val addIntakeTime = AddIntakeTime()
        val courseDrugTitle = CourseDrugTitle()
    }
}