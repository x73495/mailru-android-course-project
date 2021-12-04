package company.vk.education.androidcourse.rememberthepills.drug.model

import company.vk.education.androidcourse.rememberthepills.components.storage.entity.DrugEntity

class DrugRepositoryMapper {
    fun convertToDrugEntity(drug: Drug): DrugEntity {
        return DrugEntity(
            drug.id,
            drug.name,
            drug.drugType.id,
            drug.measurementType.id
        )
    }
}