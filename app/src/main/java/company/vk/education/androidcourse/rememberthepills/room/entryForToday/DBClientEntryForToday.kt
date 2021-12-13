package company.vk.education.androidcourse.rememberthepills.room.entryForToday

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.fragment.app.FragmentActivity
import company.vk.education.androidcourse.rememberthepills.MainActivity
import company.vk.education.androidcourse.rememberthepills.models.intakeTime.IntakeTime
import company.vk.education.androidcourse.rememberthepills.notifications.Notifications
import company.vk.education.androidcourse.rememberthepills.notifications.NotificationsReceiver
import company.vk.education.androidcourse.rememberthepills.room.course.EntityCourse
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep
import java.time.LocalTime

class DBClientEntryForToday(private val mainActivity: FragmentActivity) {

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
        result = db.daoEntryForToday().getAll()
    }

    suspend fun insertAll(vararg entitiesEntryForToday: EntityEntryForToday) =
        withContext(Dispatchers.IO) {
            db.daoEntryForToday().insertAll(*entitiesEntryForToday)
            result = true
            afterAction(entitiesEntryForToday.asList().toMutableList())
        }

    suspend fun delete(entityEntryForToday: EntityEntryForToday) = withContext(Dispatchers.IO) {
        db.daoEntryForToday().delete(entityEntryForToday)
        result = true
        afterAction()
    }

    suspend fun deleteEverything() = withContext(Dispatchers.IO) {
        db.daoEntryForToday().deleteEverything()
        result = true
        afterAction()
    }

    suspend fun update(entityEntryForToday: EntityEntryForToday) = withContext(Dispatchers.IO) {
        db.daoEntryForToday().update(entityEntryForToday)
        result = true
    }

    suspend fun getByIntakeTime(intakeTime: IntakeTime, relatedCourseID: Int) =
        withContext(Dispatchers.IO) {
            val tempResult = db.daoEntryForToday().findByIntakeTime(intakeTime, relatedCourseID)
            result = tempResult ?: false
        }

    private fun afterAction(targetEntries: MutableList<EntityEntryForToday> = mutableListOf()) {
        if (targetEntries.isEmpty()) {
            val allEntries = db.daoEntryForToday().getAll()
            targetEntries.addAll(allEntries)
        }

        targetEntries.forEach { entry ->
            val intakeTimeParsed =
                LocalTime.parse(entry.intakeTime.toString())

            if (intakeTimeParsed.isAfter(LocalTime.now())) {
                val courseTarget: EntityCourse = db.daoCourse().findByID(entry.relatedCourseID)
                val drugTarget: EntityDrug = db.daoDrug().findByID(courseTarget.drugID)

                Notifications(mainActivity.applicationContext).sendNotification(
                    "Pills time!",
                    "Время принять ${drugTarget.name}",
                    entry.intakeTime.hour,
                    entry.intakeTime.minute
                )

                // TODO: is this necessary?
                sleep(100)
            }
        }
    }
}
