package company.vk.education.androidcourse.rememberthepills.components.form.model

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload

class NumberedTextFieldDataItem(
    val id: Int,
    val number: Int?,
    val hint: String,
    var maxLength: Int,
    val editingNumberHandler: (Int?) -> Unit
) : BaseDataItem, BasePayload {

    override var viewType: Int = FormViewType.numberedTextField.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is NumberedTextFieldDataItem) {
            number == item.number && hint == item.hint
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is NumberedTextFieldDataItem) {
            id == item.id
        } else {
            false
        }
    }
}