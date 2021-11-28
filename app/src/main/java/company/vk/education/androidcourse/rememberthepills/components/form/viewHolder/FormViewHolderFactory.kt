package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolderAbstractFactory
import company.vk.education.androidcourse.rememberthepills.components.form.model.FormViewType

class FormViewHolderFactory : BaseViewHolderAbstractFactory() {
    override fun makeViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder {
        return when(viewType) {
            FormViewType.autoComplitedTextField.viewType -> AutocomplitedTextFieldViewHolder.from(parent)
            FormViewType.datedTextField.viewType -> DatedTextFieldViewHolder.from(parent)
            FormViewType.numberedTextField.viewType -> NumberedTextFieldViewHolder.from(parent)
            FormViewType.textedTextField.viewType -> TextedTextFieldViewHolder.from(parent)
            FormViewType.sectionHeader.viewType -> SectionHeaderViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }
}