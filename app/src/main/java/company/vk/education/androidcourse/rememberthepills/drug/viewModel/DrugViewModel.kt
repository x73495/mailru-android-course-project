package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode

class DrugViewModel(
    private val mode: FormScreenMode,
    private val id: Int?,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val mapper = DrugViewModelMapper(resourceProvider)
    private var viewState: DrugViewState = DrugViewState(
        drugId = id,
        drugItems = DrugTypeItem.values(),
        selectedTypeDrugItem = DrugTypeItem.values().first(),
        drugNameText = null
    )

    // TODO TEMPORARY
    fun dataItems(): List<BaseDataItem> {
        return mapper.createDataItems(viewState)
    }
}

class DrugViewModelFactory(
    private val mode: FormScreenMode,
    private val id: Int?,
    private val resourceProvider: ResourceProvider
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrugViewModel(mode, id, resourceProvider) as T
    }
}