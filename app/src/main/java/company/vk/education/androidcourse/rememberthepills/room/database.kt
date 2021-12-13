package company.vk.education.androidcourse.rememberthepills.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import company.vk.education.androidcourse.rememberthepills.room.course.DaoCourse
import company.vk.education.androidcourse.rememberthepills.room.course.EntityCourse
import company.vk.education.androidcourse.rememberthepills.room.drug.DaoDrug
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug
import company.vk.education.androidcourse.rememberthepills.room.entryForToday.DaoEntryForToday
import company.vk.education.androidcourse.rememberthepills.room.entryForToday.EntityEntryForToday

@Database(entities = [EntityDrug::class, EntityCourse::class, EntityEntryForToday::class], version = 1)
@TypeConverters(Converters::class)
abstract class RTPDatabase : RoomDatabase() {
    abstract fun daoDrug(): DaoDrug
    abstract fun daoCourse(): DaoCourse
    abstract fun daoEntryForToday(): DaoEntryForToday
}
