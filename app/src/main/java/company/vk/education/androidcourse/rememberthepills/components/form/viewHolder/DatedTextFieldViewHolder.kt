package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.databinding.ItemAutocomplitedTextInputBinding
import company.vk.education.androidcourse.rememberthepills.databinding.ItemDatedTextInputBinding

class DatedTextFieldViewHolder private constructor(private val binding: ItemDatedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem) {
        // TODO: must be implemented
    }

    companion object {
        fun from(parent: ViewGroup): DatedTextFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDatedTextInputBinding.inflate(layoutInflater, parent, false)
            return DatedTextFieldViewHolder(binding)
        }
    }
}