package company.vk.education.androidcourse.rememberthepills.schedule.viewModel

import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleListItem
import company.vk.education.androidcourse.rememberthepills.schedule.view.adapter.item.ScheduleListDataItem
import java.text.SimpleDateFormat

class ScheduleListViewModelMapper(
    private val resourceProvider: ResourceProvider,
    private val delegate: ScheduleListViewModelMapper.Delegate
) {
    interface Delegate {
        fun onScheduleListSelectListener(item: ScheduleListItem)
        fun onScheduleListCheckListener(item: ScheduleListItem)
    }

    enum class ViewId {
        SCHEDULE_LIST_ITEM
    }

    fun createPresentationModel(viewState: ScheduleListViewState): ScheduleListPresentationModel {
        return ScheduleListPresentationModel(
            selectedDate = viewState.selectedDate,
            listItems = createDataItems(viewState)
        )
    }

    private fun createDataItems(viewState: ScheduleListViewState): List<BaseDataItem> {
        return viewState.scheduleListItems.map {
            val dateFormat = SimpleDateFormat("H:mm")
            val dateString = dateFormat.format(it.time)
            val measurementString = resourceProvider.getString(it.drugMeasurementType.textId)
            val subtitle = "${it.quantity} $measurementString" + " - " + dateString
            val viewId =
                ViewId.SCHEDULE_LIST_ITEM.name + it.courseId.toString() + it.drugId.toString() + it.time.toString()
            val failure =
                if (it.missed) resourceProvider.getString(R.string.missed_medication) else null
            ScheduleListDataItem(
                id = viewId,
                title = it.drugName,
                subtitle = subtitle,
                failure = failure,
                checked = it.checked,
                checkingHandler = {
                    delegate.onScheduleListCheckListener(it)
                },
                editHandler = {
                    delegate.onScheduleListSelectListener(it)
                }
            )
        }
    }
}