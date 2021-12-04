package company.vk.education.androidcourse.rememberthepills.drug.model

import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

data class Drug(
    val id: Long = 0,
    val name: String,
    val measurementType: TextedItem,
    val drugType: TextedItem
)