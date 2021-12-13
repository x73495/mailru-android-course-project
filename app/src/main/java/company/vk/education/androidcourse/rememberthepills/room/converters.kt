package company.vk.education.androidcourse.rememberthepills.room

import androidx.room.TypeConverter
import company.vk.education.androidcourse.rememberthepills.models.intakeTime.IntakeTime
import java.time.LocalDate
import java.time.LocalTime

class Converters {
    @TypeConverter
    fun localTimeFromString(timeAsString: String): LocalTime {
        return LocalTime.parse(timeAsString)
    }

    @TypeConverter
    fun localTimeToString(localTime: LocalTime): String {
        return localTime.toString()
    }

    @TypeConverter
    fun localDateFromString(dateAsString: String): LocalDate {
        return LocalDate.parse(dateAsString)
    }

    @TypeConverter
    fun localDateToString(localDate: LocalDate): String {
        return localDate.toString()
    }

    @TypeConverter
    fun intakeTimeListFromString(intakeTimeListAsString: String): List<IntakeTime> {
        val result = intakeTimeListAsString.split(", ")
            .map { IntakeTime(it.split(':')[0].toInt(), it.split(':')[1].toInt()) }
        return result
    }

    @TypeConverter
    fun intakeTimeListToStringList(intakeTimeList: List<IntakeTime>): String {
        val result = intakeTimeList.map { "${it.hour}:${it.minute}" }.toString().drop(1).dropLast(1)
        return result
    }

    @TypeConverter
    fun intakeTimeToString(intakeTime: IntakeTime): String {
        return "${intakeTime.hour}:${intakeTime.minute}"
    }

    @TypeConverter
    fun stringToIntakeTime(intakeTimeAsString: String): IntakeTime {
        return IntakeTime(
            intakeTimeAsString.split(':')[0].toInt(),
            intakeTimeAsString.split(':')[1].toInt()
        )
    }
}
