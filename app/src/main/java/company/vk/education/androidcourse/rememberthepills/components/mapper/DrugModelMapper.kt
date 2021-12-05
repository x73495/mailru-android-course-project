package company.vk.education.androidcourse.rememberthepills.components.mapper

import company.vk.education.androidcourse.rememberthepills.components.models.Drug
import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.DrugEntity

class DrugModelMapper {
    fun convertToDrugEntity(drug: Drug): DrugEntity {
        return DrugEntity(
            drug.id,
            drug.name,
            drug.drugType.id,
            drug.measurementType.id
        )
    }

    fun convertToDrug(drugEntity: DrugEntity): Drug {
        val measurementType =
            MeasurementItem.values().find { it.id == drugEntity.measurementTypeId }
                ?: MeasurementItem.Units
        val drugType =
            DrugTypeItem.values().find { it.id == drugEntity.drugTypeId }
                ?: DrugTypeItem.Tablet
        return Drug(
            drugEntity.id,
            drugEntity.name,
            measurementType,
            drugType
        )
    }
}