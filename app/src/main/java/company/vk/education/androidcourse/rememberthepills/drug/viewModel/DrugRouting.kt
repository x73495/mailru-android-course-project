package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting

class DrugRouting {
    class None: BaseRouting()
    class Back: BaseRouting()

    companion object {
        val none = DrugRouting.None()
        val back = DrugRouting.Back()
    }
}