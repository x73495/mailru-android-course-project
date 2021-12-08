package company.vk.education.androidcourse.rememberthepills.schedule.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.databinding.ItemViewCourseListBinding
import company.vk.education.androidcourse.rememberthepills.schedule.view.adapter.item.ScheduleListDataItem

class ScheduleListViewHolder private constructor(private val binding: ItemViewCourseListBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is ScheduleListDataItem) {
            binding.textTitle.text = item.title
            binding.textSubtitle.text = item.subtitle
            binding.checkboxCourseEntryIsDone.isChecked = item.checked
            if (item.failure != null) {
                binding.textCourseEntryMissed.visibility = View.VISIBLE
                binding.textCourseEntryMissed.text = item.failure
            } else {
                binding.textCourseEntryMissed.visibility = View.GONE
            }
            binding.checkboxCourseEntryIsDone.setOnCheckedChangeListener { _, _ ->
                item.checkingHandler()
            }
            binding.buttonCourseEntryEdit.setOnClickListener {
                item.editHandler()
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): ScheduleListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemViewCourseListBinding.inflate(layoutInflater, parent, false)
            return ScheduleListViewHolder(binding)
        }
    }
}