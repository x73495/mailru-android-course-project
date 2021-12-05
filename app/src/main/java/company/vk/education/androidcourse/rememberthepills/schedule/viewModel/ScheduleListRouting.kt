package company.vk.education.androidcourse.rememberthepills.schedule.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting

class ScheduleListRouting {
    class None: BaseRouting()
    class DrugList: BaseRouting()

    companion object {
        val none = None()
        val drugList = DrugList()
    }
}