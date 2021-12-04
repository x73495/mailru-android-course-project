package company.vk.education.androidcourse.rememberthepills.drugList.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugPresentationModel
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewModel
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListItem
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListRepository

class DrugListViewModel(
    private val resourceProvider: ResourceProvider,
    private val drugListRepository: DrugListRepository
) : ViewModel(),
    DrugListViewModelMapper.Delegate {

    private val mapper = DrugListViewModelMapper(resourceProvider, this)
    private var viewState: DrugListViewState = DrugListViewState(
        drugs = listOf()
    )

    val presentationModel: MutableLiveData<DrugPresentationModel> by lazy {
        MutableLiveData<DrugPresentationModel>()
    }

    // Mapper handlers

    override fun onDrugSelectListener(item: DrugListItem) {

    }
}

class DrugListViewModelFactory(
    private val resourceProvider: ResourceProvider,
    private val drugListRepository: DrugListRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrugListViewModel(resourceProvider, drugListRepository) as T
    }
}