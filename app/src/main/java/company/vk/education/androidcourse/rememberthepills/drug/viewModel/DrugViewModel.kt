package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.MeasurementItem
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem
import company.vk.education.androidcourse.rememberthepills.drug.model.DrugRepository

class DrugViewModel(
    private val mode: FormScreenMode,
    private val id: Int?,
    private val resourceProvider: ResourceProvider,
    private val drugRepository: DrugRepository
) : ViewModel(), DrugViewModelMapper.Delegate  {

    private val mapper = DrugViewModelMapper(resourceProvider, this)
    private var viewState: DrugViewState = DrugViewState(
        drugId = id,
        drugItems = DrugTypeItem.values(),
        screenMode = mode,
        selectedDrugTypeItem = DrugTypeItem.values().first(),
        drugNameText = null,
        measurementItems = MeasurementItem.values(),
        selectedMeasurementItem = MeasurementItem.values().first(),
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

    override fun onMeasurementTypeSelectListener(item: TextedItem) {
        viewState.selectedMeasurementItem = item
        updateUI()
    }
}

class DrugViewModelFactory(
    private val mode: FormScreenMode,
    private val id: Int?,
    private val resourceProvider: ResourceProvider,
    private val drugRepository: DrugRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrugViewModel(mode, id, resourceProvider, drugRepository) as T
    }
}