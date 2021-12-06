package company.vk.education.androidcourse.rememberthepills.schedule.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import java.util.Date

data class ScheduleListPresentationModel(
    val selectedDate: Date,
    val listItems: List<BaseDataItem>
)