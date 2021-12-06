package company.vk.education.androidcourse.rememberthepills.components.storage.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import company.vk.education.androidcourse.rememberthepills.components.storage.dao.CourseDao
import company.vk.education.androidcourse.rememberthepills.components.storage.dao.DrugDao
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.CourseDateTimeChecking
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.CourseEntity
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.DrugEntity
import company.vk.education.androidcourse.rememberthepills.components.storage.entity.IntakeTimeEntity
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [DrugEntity::class,
        CourseDateTimeChecking::class,
        CourseEntity::class,
        IntakeTimeEntity::class], version = 3
)
abstract class RTPRoomDatabase : RoomDatabase() {

    abstract fun drugDao(): DrugDao
    abstract fun courseDao(): CourseDao

    companion object {
        @Volatile
        private var INSTANCE: RTPRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): RTPRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RTPRoomDatabase::class.java,
                    "rtp_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(RTPDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class RTPDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d("RTPRoomDatabase", "onCreate")
            }
        }
    }
}