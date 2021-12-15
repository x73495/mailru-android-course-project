package company.vk.education.androidcourse.rememberthepills.room.drug

import android.util.Log
import androidx.fragment.app.FragmentActivity
import company.vk.education.androidcourse.rememberthepills.MainActivity
import company.vk.education.androidcourse.rememberthepills.analytics.Analytics
import company.vk.education.androidcourse.rememberthepills.analytics.models.drug.DrugAdditionRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        val api = Analytics()
        entitiesDrug.forEach { entity ->
            CoroutineScope(Dispatchers.IO).launch {
                val request = api.retrofitAnalyticService.postDrug(DrugAdditionRequest(entity.name, entity.type))
                Log.i("analytics", request.message)
            }
        }
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