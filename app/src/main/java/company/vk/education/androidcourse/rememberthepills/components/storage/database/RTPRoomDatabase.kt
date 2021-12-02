package company.vk.education.androidcourse.rememberthepills.components.storage.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class RTPRoomDatabase : RoomDatabase(){

    companion object {
        @Volatile
        private var INSTANCE: RTPRoomDatabase? = null

        fun getDatabase(context: Context): RTPRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RTPRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}