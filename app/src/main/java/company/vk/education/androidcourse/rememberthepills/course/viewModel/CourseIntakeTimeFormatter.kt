package company.vk.education.androidcourse.rememberthepills.course.viewModel

class CourseIntakeTimeFormatter {
    fun hoursAndMinutes(timeInMinutes: Int): Pair<Int, Int> {
        return Pair(timeInMinutes / 60, timeInMinutes % 60)
    }

    fun hoursAndMinutesString(timeInMinutes: Int): String {
        val values = hoursAndMinutes(timeInMinutes)
        val hours = values.first
        var minutes = values.second

        val minutesString = if (minutes > 9) minutes else "0${minutes}"
        val resultString = "${hours}:$minutesString"
        return resultString
    }

    fun timeInMinutes(hours: Int, minutes: Int): Int {
        return hours * 60 + minutes
    }
}