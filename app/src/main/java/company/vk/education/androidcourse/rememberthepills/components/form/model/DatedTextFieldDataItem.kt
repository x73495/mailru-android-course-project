package company.vk.education.androidcourse.rememberthepills.components.form.model

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import java.text.DateFormat

class DatedTextFieldDataItem(
    val id: Int,
    val hint: String,
    val dateInMilliseconds: Long?,
    val dateFormat: DateFormat,
    val startedSelectDateHandler: () -> Unit
) : BaseDataItem, BasePayload {

    override var viewType: Int = FormViewType.datedTextField.viewType

    override fun contentsTheSame(item: BaseDataItem): Boolean {
        return if (item is DatedTextFieldDataItem) {
            dateInMilliseconds == item.dateInMilliseconds && hint == item.hint
        } else {
            false
        }
    }

    override fun itemsTheSame(item: BaseDataItem): Boolean {
        return if (item is DatedTextFieldDataItem) {
            id == item.id
        } else {
            false
        }
    }
}