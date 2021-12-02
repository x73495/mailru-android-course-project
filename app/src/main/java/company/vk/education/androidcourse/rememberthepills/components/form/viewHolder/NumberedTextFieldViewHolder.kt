package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.text.InputFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.form.model.NumberedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemNumberedTextInputBinding

class NumberedTextFieldViewHolder private constructor(private val binding: ItemNumberedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is NumberedTextFieldDataItem) {
            val numberText = item.number?.toString()
            binding.itemNumberedTextInputLayout.hint = item.hint
            binding.itemNumberedTextInputEditText.setText(numberText)

            val maxLengthFilter = InputFilter.LengthFilter(item.maxLength)
            binding.itemNumberedTextInputEditText.filters = arrayOf(maxLengthFilter)

            val cursorIndex = numberText?.length ?: 0
            binding.itemNumberedTextInputEditText.setSelection(cursorIndex)
            binding.itemNumberedTextInputEditText.doOnTextChanged { text, _, _, _ ->
                item.editingNumberHandler(text.toString().toIntOrNull())
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): NumberedTextFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNumberedTextInputBinding.inflate(layoutInflater, parent, false)
            return NumberedTextFieldViewHolder(binding)
        }
    }
}