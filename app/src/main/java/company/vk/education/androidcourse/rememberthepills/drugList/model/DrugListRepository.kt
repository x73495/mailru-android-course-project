package company.vk.education.androidcourse.rememberthepills.drugList.model

import company.vk.education.androidcourse.rememberthepills.components.storage.dao.DrugDao
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.DrugEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DrugListRepository(
    private val drugDao: DrugDao,
    private val mapper: DrugListRepositoryMapper
) {
    val allDrugs: Flow<List<DrugListItem>> = drugDao.getAllDrugs().map {
       it -> it.map { mapper.convertToDrugListItem(it) }
    }
}