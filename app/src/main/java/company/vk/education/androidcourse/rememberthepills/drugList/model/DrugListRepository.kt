package company.vk.education.androidcourse.rememberthepills.drugList.model

import company.vk.education.androidcourse.rememberthepills.components.storage.dao.DrugDao

class DrugListRepository(
    private val drugDao: DrugDao,
    private val mapper: DrugListRepositoryMapper
) {
}