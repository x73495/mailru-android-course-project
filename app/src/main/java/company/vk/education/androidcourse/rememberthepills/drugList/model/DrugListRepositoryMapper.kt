package company.vk.education.androidcourse.rememberthepills.drugList.model

import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.DrugEntity

class DrugListRepositoryMapper {

    fun convertToDrugListItem(drugEntity: DrugEntity): DrugListItem {
        val drugType =
            DrugTypeItem.values().find { it.id == drugEntity.drugTypeId } ?: DrugTypeItem.Tablet
        val measurementType =
            MeasurementItem.values().find { it.id == drugEntity.measurementTypeId }
                ?: MeasurementItem.Tablespoons
        return DrugListItem(
            drugEntity.id,
            drugEntity.name,
            drugType,
            measurementType
        )
    }
}