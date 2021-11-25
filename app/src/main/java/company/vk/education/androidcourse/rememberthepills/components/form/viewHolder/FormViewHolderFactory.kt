package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolderAbstractFactory
import company.vk.education.androidcourse.rememberthepills.components.form.model.FormViewType

class FormViewHolderFactory : BaseViewHolderAbstractFactory() {
    override fun makeViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder {
        return when(viewType) {
            FormViewType.AUTOCOMPLITED_TEXT_FIELD.ordinal -> AutocomplitedTextFieldViewHolder.from(parent)
            FormViewType.DATED_TEXT_FIELD.ordinal -> DatedTextFieldViewHolder.from(parent)
            FormViewType.NUMBERED_TEXT_FIELD.ordinal -> NumberedTextFieldViewHolder.from(parent)
            FormViewType.TEXTED_TEXT_FIELD.ordinal -> TextedTextFieldViewHolder.from(parent)
            FormViewType.SECTION_HEADER.ordinal -> SectionHeaderViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }
}