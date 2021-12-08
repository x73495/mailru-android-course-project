package company.vk.education.androidcourse.rememberthepills.course.viewModel

class CourseIntakeTimeFormatter {
    val millisecondsInMinute: Long = 60 * 1000
    fun hoursAndMinutes(timeInMilliseconds: Long): Pair<Int, Int> {
        val timeInMinutes: Long = timeInMilliseconds / millisecondsInMinute
        val minutes: Int = (timeInMinutes % 60).toInt()
        val hours: Int = (timeInMinutes / 60).toInt()
        return Pair(hours, minutes)
    }

    fun hoursAndMinutesString(timeInMilliseconds: Long): String {
        val values = hoursAndMinutes(timeInMilliseconds)
        val hours = values.first
        var minutes = values.second

        val minutesString = if (minutes > 9) minutes else "0${minutes}"
        val resultString = "${hours}:$minutesString"
        return resultString
    }

    fun timeInMilliseconds(hours: Int, minutes: Int): Long {
        return (hours.toLong() * 60L + minutes.toLong()) * millisecondsInMinute
    }
}