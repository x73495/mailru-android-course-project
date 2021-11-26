package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.databinding.ItemDatedTextInputBinding
import company.vk.education.androidcourse.rememberthepills.databinding.ItemNumberedTextInputBinding

class NumberedTextFieldViewHolder private constructor(private val binding: ItemNumberedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: must be implemented
    }

    companion object {
        fun from(parent: ViewGroup): NumberedTextFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemNumberedTextInputBinding.inflate(layoutInflater, parent, false)
            return NumberedTextFieldViewHolder(binding)
        }
    }
}