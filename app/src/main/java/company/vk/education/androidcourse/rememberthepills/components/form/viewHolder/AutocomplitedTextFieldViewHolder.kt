package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.form.model.AutocomplitedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemAutocomplitedTextInputBinding

class AutocomplitedTextFieldViewHolder private constructor(private val binding: ItemAutocomplitedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is AutocomplitedTextFieldDataItem) {
            binding.inputAutocomplitedTextInputLayout.hint = item.hint

            val context = binding.inputAutocomplitedTextView.context
            val textedItemStrings = item.textedItems.map { context.getString(it.textId) }
            val adapter = ArrayAdapter(context, R.layout.item_text_view_drop_down_list, textedItemStrings)
            binding.inputAutocomplitedTextView.setText(context.getString(item.selectedTextedItem.textId))
            binding.inputAutocomplitedTextView.dismissDropDown()
            binding.inputAutocomplitedTextView.setAdapter(adapter)

            binding.inputAutocomplitedTextView.onItemClickListener =
                (AdapterView.OnItemClickListener { _, _, position, _ ->
                    item.selectedItemHandler(item.textedItems[position])
                })
        }
    }

    companion object {
        fun from(parent: ViewGroup): AutocomplitedTextFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemAutocomplitedTextInputBinding.inflate(layoutInflater, parent, false)
            return AutocomplitedTextFieldViewHolder(binding)
        }
    }
}