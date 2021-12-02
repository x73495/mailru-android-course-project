package company.vk.education.androidcourse.rememberthepills.course.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.IntakeTimeDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemIntakeTimeListBinding

class CourseIntakeTimeViewHolder private constructor(private val binding: ItemIntakeTimeListBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is IntakeTimeDataItem) {
            binding.textIntakeTime.text = item.timeString
            binding.imageIntakeTimeRemove.setOnClickListener {
                item.removeTimeHandler()
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): CourseIntakeTimeViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemIntakeTimeListBinding.inflate(layoutInflater, parent, false)
            return CourseIntakeTimeViewHolder(binding)
        }
    }
}