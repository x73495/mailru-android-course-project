package company.vk.education.androidcourse.rememberthepills.schedule.viewModel

import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleListItem
import java.util.Date

data class ScheduleListViewState(
    var selectedDate: Date,
    var scheduleListItems: List<ScheduleListItem>
)