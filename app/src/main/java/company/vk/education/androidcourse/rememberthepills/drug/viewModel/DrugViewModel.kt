package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.form.model.AutocomplitedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.TextedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode

class DrugViewModel(
    private val mode: FormScreenMode,
    private val id: Int?,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    // TODO TEMPORARY
    fun dataItems(): List<BaseDataItem> {
        return listOf(
            TextedTextFieldDataItem(
                0,
                "Фуфломицин",
                resourceProvider.getString(R.string.drug_name)
            ),
            AutocomplitedTextFieldDataItem(
                1,
                DrugTypeItem.Drops,
                resourceProvider.getString(R.string.drug_type)
            ),
            TextedTextFieldDataItem(
                2,
                "Фуфломицин",
                resourceProvider.getString(R.string.drug_name)
            ),
            AutocomplitedTextFieldDataItem(
                3,
                DrugTypeItem.Drops,
                resourceProvider.getString(R.string.drug_type)
            ),
            TextedTextFieldDataItem(
                4,
                "Фуфломицин",
                resourceProvider.getString(R.string.drug_name)
            ),
            AutocomplitedTextFieldDataItem(
                5,
                DrugTypeItem.Drops,
                resourceProvider.getString(R.string.drug_type)
            ),
            TextedTextFieldDataItem(
                6,
                "Фуфломицин",
                resourceProvider.getString(R.string.drug_name)
            ),
            AutocomplitedTextFieldDataItem(
                7,
                DrugTypeItem.Drops,
                resourceProvider.getString(R.string.drug_type)
            ),
            TextedTextFieldDataItem(
                8,
                "Фуфломицин",
                resourceProvider.getString(R.string.drug_name)
            ),
            AutocomplitedTextFieldDataItem(
                9,
                DrugTypeItem.Drops,
                resourceProvider.getString(R.string.drug_type)
            ),
            TextedTextFieldDataItem(
                10,
                "Фуфломицин",
                resourceProvider.getString(R.string.drug_name)
            ),
            AutocomplitedTextFieldDataItem(
                11,
                DrugTypeItem.Drops,
                resourceProvider.getString(R.string.drug_type)
            ),
            TextedTextFieldDataItem(
                12,
                "Фуфломицин",
                resourceProvider.getString(R.string.drug_name)
            ),
            AutocomplitedTextFieldDataItem(
                13,
                DrugTypeItem.Drops,
                resourceProvider.getString(R.string.drug_type)
            ),
            TextedTextFieldDataItem(
                14,
                "Фуфломицин",
                resourceProvider.getString(R.string.drug_name)
            ),
            AutocomplitedTextFieldDataItem(
                15,
                DrugTypeItem.Drops,
                resourceProvider.getString(R.string.drug_type)
            )
        )
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