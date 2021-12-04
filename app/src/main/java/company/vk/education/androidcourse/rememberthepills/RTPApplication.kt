package company.vk.education.androidcourse.rememberthepills

import android.app.Application
import company.vk.education.androidcourse.rememberthepills.components.storage.database.RTPRoomDatabase
import company.vk.education.androidcourse.rememberthepills.course.model.CourseRepository
import company.vk.education.androidcourse.rememberthepills.course.model.CourseRepositoryMapper
import company.vk.education.androidcourse.rememberthepills.drug.model.DrugRepository
import company.vk.education.androidcourse.rememberthepills.drug.model.DrugRepositoryMapper
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListRepository
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListRepositoryMapper
import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleRepository
import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleRepositoryMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RTPApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { RTPRoomDatabase.getDatabase(this, applicationScope) }
    val drugListRepository by lazy {
        DrugListRepository(
            database.drugDao(),
            DrugListRepositoryMapper()
        )
    }
    val drugRepository by lazy {
        DrugRepository(
            database.drugDao(),
            DrugRepositoryMapper()
        )
    }
    val courseRepository by lazy {
        CourseRepository(
            database.courseDao(),
            CourseRepositoryMapper()
        )
    }
    val scheduleRepository by lazy {
        ScheduleRepository(
            database.courseDao(),
            ScheduleRepositoryMapper()
        )
    }
}