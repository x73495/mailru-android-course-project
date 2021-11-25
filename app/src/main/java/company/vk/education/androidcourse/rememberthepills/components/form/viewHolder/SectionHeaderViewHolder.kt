package company.vk.education.androidcourse.rememberthepills.components.form.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.form.model.SectionHeaderDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemSectionHeaderBinding

class SectionHeaderViewHolder private constructor(private val binding: ItemSectionHeaderBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is SectionHeaderDataItem) {
            binding.sectionHeader.setText(item.text)
        }
    }

    companion object {
        fun from(parent: ViewGroup): SectionHeaderViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemSectionHeaderBinding.inflate(layoutInflater, parent, false)
            return SectionHeaderViewHolder(binding)
        }
    }
}