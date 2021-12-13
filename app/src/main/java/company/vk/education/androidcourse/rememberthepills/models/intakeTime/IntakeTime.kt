package company.vk.education.androidcourse.rememberthepills.models.intakeTime

data class IntakeTime(
    var hour: Int,
    var minute: Int
) {
    override fun toString(): String {
        val hourAsString = if (hour < 10) "0${hour}" else hour.toString()
        val minuteAsString = if (minute < 10) "0${minute}" else minute.toString()
        return "$hourAsString:$minuteAsString"
    }
}
