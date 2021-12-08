package company.vk.education.androidcourse.rememberthepills.profile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider

class ProfileViewModel(private val resourceProvider: ResourceProvider) : ViewModel(),
    ProfileViewModelMapper.Delegate {

    private val mapper = ProfileViewModelMapper(resourceProvider, this)
    val presentationModel: MutableLiveData<ProfilePresentationModel> by lazy {
        MutableLiveData<ProfilePresentationModel>()
    }

    val routingModel: MutableLiveData<BaseRouting> by lazy {
        MutableLiveData<BaseRouting>()
    }

    init {
        updateUI()
    }

    private fun updateUI() {
        val presentationModel = mapper.createPresentationModel()
        this.presentationModel.value = presentationModel
    }

    // Mapper handlers

    override fun onDrugListItemSelectListener() {
        routingModel.value = ProfileRouting.drugList
    }

    // Fragment handlers

    fun routingDidHandle() {
        routingModel.value = ProfileRouting.none
    }
}

class ProfileViewModelFactory(
    private val resourceProvider: ResourceProvider,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(resourceProvider) as T
    }
}