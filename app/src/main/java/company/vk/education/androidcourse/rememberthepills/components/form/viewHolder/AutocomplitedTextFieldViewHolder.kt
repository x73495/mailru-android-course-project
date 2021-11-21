package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.form.model.AutocomplitedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.components.models.DrugTypeItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemAutocomplitedTextInputBinding

class AutocomplitedTextFieldViewHolder private constructor(private val binding: ItemAutocomplitedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem) {
        if (item is AutocomplitedTextFieldDataItem) {
            binding.inputAutocomplitedTextInputLayout.hint = item.hint

            val context = binding.inputAutocomplitedTextView.context
            val itemsTypes = DrugTypeItem.values().map { context.getString(it.textId) }
            val adapter = ArrayAdapter(context, R.layout.item_text_view_drop_down_list, itemsTypes)
            binding.inputAutocomplitedTextView.setText(context.getString(item.selectedItem.textId))
            binding.inputAutocomplitedTextView.setAdapter(adapter)
            //
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