package company.vk.education.androidcourse.rememberthepills.drugList.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting

class DrugListRouting {
    class None: BaseRouting()
    class CourseCreation(val drugId: Long): BaseRouting()
    class DrugEditing(val drugId: Long): BaseRouting()
    class DrugCreation: BaseRouting()

    companion object {
        val none = None()
        val drugCreation = DrugCreation()
        fun drugEditing(drugId: Long): BaseRouting {
            return DrugEditing(drugId)
        }
        fun courseCreation(drugId: Long): BaseRouting {
            return CourseCreation(drugId)
        }
    }
}