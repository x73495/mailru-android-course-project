package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

class DrugViewModel(
    private val mode: FormScreenMode,
    private val id: Int?,
    private val resourceProvider: ResourceProvider
) : ViewModel(), DrugViewModelMapper.Delegate  {

    private val mapper = DrugViewModelMapper(resourceProvider, this)
    private var viewState: DrugViewState = DrugViewState(
        drugId = id,
        drugItems = DrugTypeItem.values(),
        screenMode = mode,
        selectedDrugTypeItem = DrugTypeItem.values().first(),
        drugNameText = null
    )

    val presentationModel: MutableLiveData<DrugPresentationModel> by lazy {
        MutableLiveData<DrugPresentationModel>()
    }

    init {
        updateUI()
    }

    private fun updateUI() {
        val presentationModel = mapper.createPresentationModel(viewState)
        this.presentationModel.value = presentationModel
    }

    // Mapper handlers

    override fun onDrugTypeSelectListener(item: TextedItem) {
        viewState.selectedDrugTypeItem = item
        updateUI()
    }

    override fun onDrugNameChangeListener(text: String?) {
        viewState.drugNameText = text
        updateUI()
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