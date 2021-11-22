package company.vk.education.androidcourse.rememberthepills.components.form.model

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

class TextedTextFieldDataItem(
    val id: Int,
    val text: String?,
    val hint: String,
    val editingTextHandler: (String?) -> Unit
) : BaseDataItem {
    override var viewType: Int = FormViewType.TEXTED_TEXT_FIELD.ordinal

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