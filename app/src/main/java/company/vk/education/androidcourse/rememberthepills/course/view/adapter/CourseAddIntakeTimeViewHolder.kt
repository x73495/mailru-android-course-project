package company.vk.education.androidcourse.rememberthepills.course.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.AddIntakeTimeDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemAddIntakeTimeBinding

class CourseAddIntakeTimeViewHolder private constructor(private val binding: ItemAddIntakeTimeBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is AddIntakeTimeDataItem) {
            binding.addIntakeTime.setOnClickListener {
                item.addIntakeTimeHandler()
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): CourseAddIntakeTimeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemAddIntakeTimeBinding.inflate(layoutInflater, parent, false)
            return CourseAddIntakeTimeViewHolder(binding)
        }
    }
}