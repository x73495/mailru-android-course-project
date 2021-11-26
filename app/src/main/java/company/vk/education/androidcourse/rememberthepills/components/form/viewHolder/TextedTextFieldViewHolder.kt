package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.form.model.TextedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemTextedTextInputBinding

class TextedTextFieldViewHolder private constructor(private val binding: ItemTextedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        val item = payload ?: item
        if (item is TextedTextFieldDataItem) {
            binding.itemNumberedTextInputLayout.hint = item.hint
            binding.itemNumberedTextInputEditText.setText(item.text)
            val cursorIndex = item.text?.length ?: 0
            binding.itemNumberedTextInputEditText.setSelection(cursorIndex)
            binding.itemNumberedTextInputEditText.doOnTextChanged { text, _, _, _ ->
                item.editingTextHandler(text.toString())
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): TextedTextFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemTextedTextInputBinding.inflate(layoutInflater, parent, false)
            return TextedTextFieldViewHolder(binding)
        }
    }
}