package company.vk.education.androidcourse.rememberthepills.drug.model

import company.vk.education.androidcourse.rememberthepills.components.api.AnalyticService
import company.vk.education.androidcourse.rememberthepills.components.api.ApiModel.DrugCreateAnalyticResponse
import company.vk.education.androidcourse.rememberthepills.components.models.Drug
import company.vk.education.androidcourse.rememberthepills.components.storage.dao.DrugDao

class DrugRepository(
    private val drugDao: DrugDao,
    private val mapper: DrugRepositoryMapper,
    private val analyticService: AnalyticService
) {

    suspend fun create(drug: Drug): Long {
        val drugEntity = mapper.drugModelMapper.convertToDrugEntity(drug)
        return drugDao.insert(drugEntity)
    }

    suspend fun update(drug: Drug) {
        val drugEntity = mapper.drugModelMapper.convertToDrugEntity(drug)
        drugDao.update(drugEntity)
    }

    suspend fun delete(drug: Drug) {
        drugDao.delete(mapper.drugModelMapper.convertToDrugEntity(drug))
    }

    suspend fun drugById(id: Long): Drug {
        val drugEntity = drugDao.drugById(id)
        return mapper.drugModelMapper.convertToDrug(drugEntity)
    }

    suspend fun sendDrugCreateAnalytic(drugId: Long): DrugCreateAnalyticResponse {
        return analyticService.sendDrugCreate(drugId)
    }
}