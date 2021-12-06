package company.vk.education.androidcourse.rememberthepills.drugList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugPresentationModel
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewModel
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListItem
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListRepository
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListSourceType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DrugListViewModel(
    private val sourceType: DrugListSourceType,
    private val resourceProvider: ResourceProvider,
    private val drugListRepository: DrugListRepository
) : ViewModel(),
    DrugListViewModelMapper.Delegate {

    init {
        viewModelScope.launch {
            drugListRepository.allDrugs.collect {
                viewState.drugs = it
                updateUI()
            }
        }
    }

    private val mapper = DrugListViewModelMapper(resourceProvider, this)
    private var viewState: DrugListViewState = DrugListViewState(
        drugs = listOf()
    )

    val presentationModel: MutableLiveData<DrugListPresentationModel> by lazy {
        MutableLiveData<DrugListPresentationModel>()
    }

    val routingModel: MutableLiveData<BaseRouting> by lazy {
        MutableLiveData<BaseRouting>()
    }

    private fun updateUI() {
        val presentationModel = mapper.createPresentationModel(viewState)
        this.presentationModel.value = presentationModel
    }

    // Mapper handlers

    override fun onDrugSelectListener(item: DrugListItem) {
        when (sourceType) {
            DrugListSourceType.PROFILE -> {
                routingModel.value = DrugListRouting.drugEditing(item.id)
            }
            DrugListSourceType.SCHEDULE -> {
                routingModel.value = DrugListRouting.courseCreation(item.id)
            }
        }
    }

    // Fragment handlers
    fun routingDidHandle() {
        routingModel.value = DrugListRouting.none
    }
}

class DrugListViewModelFactory(
    private val sourceType: DrugListSourceType,
    private val resourceProvider: ResourceProvider,
    private val drugListRepository: DrugListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrugListViewModel(sourceType, resourceProvider, drugListRepository) as T
    }
}