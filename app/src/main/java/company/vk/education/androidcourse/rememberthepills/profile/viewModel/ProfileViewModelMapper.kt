package company.vk.education.androidcourse.rememberthepills.profile.viewModel

import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.profile.view.adapter.item.ProfileDataItem

class ProfileViewModelMapper(
    private val resourceProvider: ResourceProvider,
    private val delegate: ProfileViewModelMapper.Delegate
) {
    interface Delegate {
        fun onDrugListItemSelectListener()
    }

    enum class ViewId {
        PROFILE_DRUG_LIST_ITEM
    }

    fun createPresentationModel(): ProfilePresentationModel {
        return ProfilePresentationModel(
            listItems = createDataItems()
        )
    }

    private fun createDataItems(): List<BaseDataItem> {
        val drugListDataItem = ProfileDataItem(
            id = ViewId.PROFILE_DRUG_LIST_ITEM.name,
            title = resourceProvider.getString(R.string.drugs),
            selectionHandler = {
                delegate.onDrugListItemSelectListener()
            }
        )
        return listOf(drugListDataItem)
    }
}