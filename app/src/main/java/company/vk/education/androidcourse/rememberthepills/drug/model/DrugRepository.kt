package company.vk.education.androidcourse.rememberthepills.drug.model

import androidx.annotation.WorkerThread
import company.vk.education.androidcourse.rememberthepills.components.storage.dao.DrugDao

class DrugRepository(
    private val drugDao: DrugDao,
    private val mapper: DrugRepositoryMapper
) {

    suspend fun create(drug: Drug) {
        val drugEntity = mapper.convertToDrugEntity(drug)
        drugDao.insert(drugEntity)
    }

    suspend fun update(drug: Drug) {
        val drugEntity = mapper.convertToDrugEntity(drug)
        drugDao.update(drugEntity)
    }
}