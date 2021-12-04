package company.vk.education.androidcourse.rememberthepills.drug.model

import company.vk.education.androidcourse.rememberthepills.components.storage.dao.DrugDao

class DrugRepository(
    private val drugDao: DrugDao,
    private val mapper: DrugRepositoryMapper
) {
}