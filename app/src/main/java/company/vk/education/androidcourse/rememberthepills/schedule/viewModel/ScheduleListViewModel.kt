package company.vk.education.androidcourse.rememberthepills.schedule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleListItem
import company.vk.education.androidcourse.rememberthepills.schedule.model.ScheduleListRepository

class ScheduleListViewModel(
    private val resourceProvider: ResourceProvider,
    private val scheduleListListRepository: ScheduleListRepository
): ViewModel(), ScheduleListViewModelMapper.Delegate {
    private val mapper = ScheduleListViewModelMapper(resourceProvider, this)
    private var viewState: ScheduleListViewState = ScheduleListViewState(
        scheduleListItems = listOf()
    )

    val presentationModel: MutableLiveData<ScheduleListPresentationModel> by lazy {
        MutableLiveData<ScheduleListPresentationModel>()
    }

    val routingModel: MutableLiveData<BaseRouting> by lazy {
        MutableLiveData<BaseRouting>()
    }

    private fun updateUI() {
        val presentationModel = mapper.createPresentationModel(viewState)
        this.presentationModel.value = presentationModel
    }

    // Mapper handlers

    override fun onScheduleListSelectListener(item: ScheduleListItem) {

    }

    // Fragment handlers
    fun routingDidHandle() {
        routingModel.value = ScheduleListRouting.none
    }
}

class ScheduleListViewModelFactory(
    private val resourceProvider: ResourceProvider,
    private val scheduleListListRepository: ScheduleListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScheduleListViewModel(resourceProvider, scheduleListListRepository) as T
    }
}