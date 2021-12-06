package company.vk.education.androidcourse.rememberthepills.profile.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting

class ProfileRouting {
    class None: BaseRouting()
    class DrugList: BaseRouting()

    companion object {
        val none = None()
        val drugList = DrugList()
    }
}