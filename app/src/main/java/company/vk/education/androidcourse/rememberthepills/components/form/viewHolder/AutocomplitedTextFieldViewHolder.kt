package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.databinding.ItemAutocomplitedTextInputBinding

class AutocomplitedTextFieldViewHolder private constructor(private val binding: ItemAutocomplitedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem) {
    }

    companion object {
        fun from(parent: ViewGroup): AutocomplitedTextFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemAutocomplitedTextInputBinding.inflate(layoutInflater, parent, false)
            return AutocomplitedTextFieldViewHolder(binding)
        }
    }
}