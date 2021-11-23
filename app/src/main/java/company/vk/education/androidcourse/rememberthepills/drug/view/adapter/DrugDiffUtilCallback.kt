package company.vk.education.androidcourse.rememberthepills.drug.view.adapter

import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseDiffCallback
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.AutocomplitedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.form.model.TextedTextFieldDataItem

class DrugDiffUtilCallback: BaseDiffCallback() {

    override fun getChangePayload(oldItem: BaseDataItem, newItem: BaseDataItem): Any? {
        return if (oldItem is AutocomplitedTextFieldDataItem && newItem is AutocomplitedTextFieldDataItem) {
            newItem
        } else if (oldItem is TextedTextFieldDataItem && newItem is TextedTextFieldDataItem) {
            newItem
        } else {
            null
        }
    }
}