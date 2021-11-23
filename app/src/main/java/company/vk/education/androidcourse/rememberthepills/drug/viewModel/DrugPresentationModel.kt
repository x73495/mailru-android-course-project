package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem

data class DrugPresentationModel(
    val listItems: List<BaseDataItem>,
    val applyButtonTitle: String,
    val isRemoveButtonHidden: Boolean
)