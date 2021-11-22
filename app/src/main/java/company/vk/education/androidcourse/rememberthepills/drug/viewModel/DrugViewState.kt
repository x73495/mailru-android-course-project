package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

data class DrugViewState(
    val drugId: Int?,
    val drugItems: Array<out TextedItem>,
    var selectedTypeDrugItem: TextedItem,
    var drugNameText: String?
)