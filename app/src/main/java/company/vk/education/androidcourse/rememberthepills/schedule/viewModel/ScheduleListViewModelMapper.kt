package company.vk.education.androidcourse.rememberthepills.schedule.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleListItem

class ScheduleListViewModelMapper(
    private val resourceProvider: ResourceProvider,
    private val delegate: ScheduleListViewModelMapper.Delegate
) {
    interface Delegate {
        fun onScheduleListSelectListener(item: ScheduleListItem)
    }

    enum class ViewId {
        SCHEDULE_LIST_ITEM
    }

    fun createPresentationModel(viewState: ScheduleListViewState): ScheduleListPresentationModel {
        return ScheduleListPresentationModel(
            listItems = createDataItems(viewState)
        )
    }

    private fun createDataItems(viewState: ScheduleListViewState): List<BaseDataItem> {
        return listOf() // TODO: доделать
    }
}