package company.vk.education.androidcourse.rememberthepills.room.course

import android.util.Log
import androidx.fragment.app.FragmentActivity
import company.vk.education.androidcourse.rememberthepills.MainActivity
import kotlinx.coroutines.Dispatchers
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