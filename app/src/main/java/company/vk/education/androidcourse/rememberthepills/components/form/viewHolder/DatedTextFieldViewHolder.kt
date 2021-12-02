package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.form.model.DatedTextFieldDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemDatedTextInputBinding

class DatedTextFieldViewHolder private constructor(private val binding: ItemDatedTextInputBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is DatedTextFieldDataItem) {
            binding.itemDatedTextInputLayout.hint = item.hint
            if (item.dateInMilliseconds != null) {
                binding.itemDatedTextInputEditText.setText(item.dateFormat.format(item.dateInMilliseconds))
            }
            binding.itemDatedTextInputEditText.setOnClickListener {
                item.startedSelectDateHandler()
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): DatedTextFieldViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDatedTextInputBinding.inflate(layoutInflater, parent, false)
            return DatedTextFieldViewHolder(binding)
        }
    }
}