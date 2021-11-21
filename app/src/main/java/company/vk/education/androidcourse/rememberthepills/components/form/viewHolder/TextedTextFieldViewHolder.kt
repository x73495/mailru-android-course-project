package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.databinding.ItemTextedTextInputBinding

class TextedTextFieldViewHolder private constructor(private val binding: ItemTextedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem) {

    }

    companion object {
        fun from(parent: ViewGroup): TextedTextFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemTextedTextInputBinding.inflate(layoutInflater, parent, false)
            return TextedTextFieldViewHolder(binding)
        }
    }
}