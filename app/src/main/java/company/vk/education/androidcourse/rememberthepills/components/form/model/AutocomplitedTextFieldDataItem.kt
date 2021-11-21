package company.vk.education.androidcourse.rememberthepills.components.form.model

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem

class AutocomplitedTextFieldDataItem(
    val id: Int,
    val selectedItem: DrugTypeItem,
    val hint: String
) : BaseDataItem {
    override var viewType: Int = FormViewType.AUTOCOMPLITED_TEXT_FIELD.ordinal

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is AutocomplitedTextFieldDataItem) {
            selectedItem == item.selectedItem && hint == item.hint
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is AutocomplitedTextFieldDataItem) {
            id == item.id
        } else {
            false
        }
    }
}