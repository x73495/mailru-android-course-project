package company.vk.education.androidcourse.rememberthepills.course.model

import company.vk.education.androidcourse.rememberthepills.components.storage.dao.CourseDao
import company.vk.education.androidcourse.rememberthepills.components.storage.dao.DrugDao
import company.vk.education.androidcourse.rememberthepills.components.models.Drug

class CourseRepository(
    private val courseDao: CourseDao,
    private val drugDao: DrugDao,
    private val mapper: CourseRepositoryMapper
) {

    suspend fun drugById(id: Long): Drug {
        return mapper.drugModelMapper.convertToDrug(drugDao.drugById(id))
    }
}