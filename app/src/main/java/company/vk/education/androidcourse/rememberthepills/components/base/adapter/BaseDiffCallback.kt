package company.vk.education.androidcourse.rememberthepills.components.base.adapter

import androidx.recyclerview.widget.DiffUtil
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem

open class BaseDiffCallback : DiffUtil.ItemCallback<BaseDataItem>() {

    override fun areItemsTheSame(oldItem: BaseDataItem, newItem: BaseDataItem): Boolean {
        return oldItem.itemsTheSame(newItem)
    }


    override fun areContentsTheSame(oldItem: BaseDataItem, newItem: BaseDataItem): Boolean {
        return oldItem.contentsTheSame(newItem)
    }
}