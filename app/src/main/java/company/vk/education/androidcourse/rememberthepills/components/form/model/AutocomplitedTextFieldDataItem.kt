package company.vk.education.androidcourse.rememberthepills.components.form.model

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

class AutocomplitedTextFieldDataItem(
    val id: String,
    var textedItems: Array<out TextedItem>,
    val selectedTextedItem: TextedItem,
    val hint: String,
    val selectedItemHandler: (TextedItem) -> Unit
) : BaseDataItem, BasePayload {

    override var viewType: Int = FormViewType.autoComplitedTextField.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is AutocomplitedTextFieldDataItem) {
            selectedTextedItem == item.selectedTextedItem && hint == item.hint
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