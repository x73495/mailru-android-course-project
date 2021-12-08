package company.vk.education.androidcourse.rememberthepills.drugList.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.IntakeTimeDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemDrugListBinding
import company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.item.DrugListDataItem

class DrugListViewHolder private constructor(private val binding: ItemDrugListBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is DrugListDataItem) {
            binding.textDrugListTitle.text = item.name
            binding.textDrugListSubtitle.text = item.type
            binding.linearLayoutContainer.setOnClickListener {
                item.selectionHandler()
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): DrugListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDrugListBinding.inflate(layoutInflater, parent, false)
            return DrugListViewHolder(binding)
        }
    }
}