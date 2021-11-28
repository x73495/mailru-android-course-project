package company.vk.education.androidcourse.rememberthepills.components.form.model

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

class TextedTextFieldDataItem(
    val id: String,
    val text: String?,
    val hint: String,
    val editingTextHandler: (String?) -> Unit
) : BaseDataItem, BasePayload {

    override var viewType: Int = FormViewType.textedTextField.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is TextedTextFieldDataItem) {
            text == item.text && hint == item.hint
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is TextedTextFieldDataItem) {
            id == item.id
        } else {
            false
        }
    }
}