package company.vk.education.androidcourse.rememberthepills.room.course

import android.util.Log
import androidx.fragment.app.FragmentActivity
import company.vk.education.androidcourse.rememberthepills.MainActivity
import company.vk.education.androidcourse.rememberthepills.analytics.Analytics
import company.vk.education.androidcourse.rememberthepills.analytics.models.course.CourseAdditionRequest
import company.vk.education.androidcourse.rememberthepills.analytics.models.drug.DrugAdditionRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

class DBClientCourses(mainActivity: FragmentActivity) {

    var result: Any? = null
    private val db = (mainActivity as MainActivity).db

    fun awaitResult(): Any? {
        while (result == null) {
            sleep(16)
            // TODO: awful
        }
        val tempResult = result
        result = null
        return tempResult
    }

    suspend fun getAll() = withContext(Dispatchers.IO) {
        result = db.daoCourse().getAll() ?: listOf<EntityCourse>()
    }

    suspend fun loadAllByIds(entityCourseIDs: IntArray) = withContext(Dispatchers.IO) {
        result = db.daoCourse().loadAllByIds(entityCourseIDs)
    }

    suspend fun insertAll(vararg entitiesCourse: EntityCourse) = withContext(Dispatchers.IO) {
        db.daoCourse().insertAll(*entitiesCourse)
        result = true

        val api = Analytics()
        entitiesCourse.forEach { entity ->
            val drugName = db.daoDrug().findByID(entity.drugID).name

            var intakeTimesAsString = entity.intakeTimes.toString()
            intakeTimesAsString = intakeTimesAsString.substring(1, intakeTimesAsString.length - 1)

            CoroutineScope(Dispatchers.IO).launch {
                val request = api.retrofitAnalyticService.postCourse(
                    CourseAdditionRequest(
                        drugName,
                        entity.measurement,
                        entity.amount.toString(),
                        entity.foodDependency,
                        entity.dateStart.toString(),
                        entity.dateEnd.toString(),
                        entity.frequencyInDays.toString(),
                        intakeTimesAsString
                    )
                )
                Log.i("analytics", request.message)
            }
        }
    }

    suspend fun delete(entityCourse: EntityCourse) = withContext(Dispatchers.IO) {
        db.daoCourse().delete(entityCourse)
        result = true
    }

    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        val targetEntity = db.daoCourse().findByID(id)
        db.daoCourse().delete(targetEntity)
        result = true
    }

    suspend fun deleteByDrugID(drugID: Int) = withContext(Dispatchers.IO) {
        val targetEntities = db.daoCourse().findAllByDrugID(drugID)
        db.daoCourse().deleteAll(targetEntities)
        result = true
    }

    suspend fun update(entityCourse: EntityCourse) = withContext(Dispatchers.IO) {
        db.daoCourse().update(entityCourse)
        result = true
    }

    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
        result = db.daoCourse().findByID(id)
    }
}