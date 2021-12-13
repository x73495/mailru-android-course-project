package company.vk.education.androidcourse.rememberthepills.room.drug

import androidx.fragment.app.FragmentActivity
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import company.vk.education.androidcourse.rememberthepills.MainActivity
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

class DBClientDrugs(mainActivity: FragmentActivity) {

    private var result: Any? = null
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
        result = db.daoDrug().getAll()
    }

    suspend fun loadAllByIds(entityDrugIDs: IntArray) = withContext(Dispatchers.IO) {
        result = db.daoDrug().loadAllByIds(entityDrugIDs)
    }

    suspend fun findByName(name: String, type: String) = withContext(Dispatchers.IO) {
        result = db.daoDrug().findByName(name, type)
    }

    suspend fun insertAll(vararg entitiesDrug: EntityDrug) = withContext(Dispatchers.IO) {
        db.daoDrug().insertAll(*entitiesDrug)
        result = true
    }

    suspend fun delete(entityDrug: EntityDrug) = withContext(Dispatchers.IO) {
        db.daoDrug().delete(entityDrug)
        result = true
    }

    suspend fun deleteByID(id: Int) = withContext(Dispatchers.IO) {
        val targetEntity = db.daoDrug().findByID(id)
        db.daoDrug().delete(targetEntity)
        result = true
    }

    suspend fun update(entityDrug: EntityDrug) = withContext(Dispatchers.IO) {
        db.daoDrug().update(entityDrug)
        result = true
    }

    suspend fun getByID(id: Int) = withContext(Dispatchers.IO) {
        result = db.daoDrug().findByID(id)
    }
}