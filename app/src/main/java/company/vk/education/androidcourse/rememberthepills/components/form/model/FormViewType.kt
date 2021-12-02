package company.vk.education.androidcourse.rememberthepills.components.form.model

import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseViewType

class FormViewType {
    class AutoComplitedTextField: BaseViewType()
    class DatedTextField: BaseViewType()
    class NumberedTextField: BaseViewType()
    class TextedTextField: BaseViewType()
    class SectionHeader: BaseViewType()

    companion object {
        val autoComplitedTextField = AutoComplitedTextField()
        val datedTextField = DatedTextField()
        val numberedTextField = NumberedTextField()
        val textedTextField = TextedTextField()
        val sectionHeader = SectionHeader()
    }
}