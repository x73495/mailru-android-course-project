package company.vk.education.androidcourse.rememberthepills.drugList.model

import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem

data class DrugListItem(
    val id: Long,
    val name: String,
    val drugType: DrugTypeItem,
    val measurementType: MeasurementItem
)