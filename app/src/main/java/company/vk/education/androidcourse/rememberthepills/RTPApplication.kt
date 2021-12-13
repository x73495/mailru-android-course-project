package company.vk.education.androidcourse.rememberthepills

import android.app.Application
import company.vk.education.androidcourse.rememberthepills.components.api.RTPApiDependency
import company.vk.education.androidcourse.rememberthepills.components.mapper.DrugModelMapper
import company.vk.education.androidcourse.rememberthepills.components.storage.database.RTPRoomDatabase
import company.vk.education.androidcourse.rememberthepills.course.model.CourseRepository
import company.vk.education.androidcourse.rememberthepills.course.model.CourseRepositoryMapper
import company.vk.education.androidcourse.rememberthepills.drug.model.DrugRepository
import company.vk.education.androidcourse.rememberthepills.drug.model.DrugRepositoryMapper
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListRepository
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListRepositoryMapper
import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleListRepository
import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleListRepositoryMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RTPApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { RTPRoomDatabase.getDatabase(this, applicationScope) }
    private val apiDependency = RTPApiDependency()

    val drugListRepository by lazy {
        DrugListRepository(
            database.drugDao(),
            DrugListRepositoryMapper()
        )
    }
    val drugRepository by lazy {
        DrugRepository(
            database.drugDao(),
            DrugRepositoryMapper(DrugModelMapper()),
            apiDependency.retrofitAnalyticService
        )
    }
    val courseRepository by lazy {
        CourseRepository(
            database.courseDao(),
            database.drugDao(),
            CourseRepositoryMapper(DrugModelMapper())
        )
    }
    val scheduleListRepository by lazy {
        ScheduleListRepository(
            database.courseDao(),
            ScheduleListRepositoryMapper(),
            apiDependency.retrofitAnalyticService
        )
    }
}