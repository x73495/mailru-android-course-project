package company.vk.education.androidcourse.rememberthepills.course.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.items.CourseDrugTitleDataItem
import company.vk.education.androidcourse.rememberthepills.databinding.ItemDrugTitleBinding

class CourseDrugTitleViewHolder private constructor(private val binding: ItemDrugTitleBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is CourseDrugTitleDataItem) {
            binding.textCourseHeadline.text = item.headline
            binding.textCourseDescription.text = item.description
        }
    }

    companion object {
        fun from(parent: ViewGroup): CourseDrugTitleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDrugTitleBinding.inflate(layoutInflater, parent, false)
            return CourseDrugTitleViewHolder(binding)
        }
    }
}