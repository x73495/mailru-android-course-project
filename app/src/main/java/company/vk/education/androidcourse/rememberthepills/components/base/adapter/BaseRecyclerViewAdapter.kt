package company.vk.education.androidcourse.rememberthepills.components.adapter.form

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolderAbstractFactory

class BaseRecyclerViewAdapter(private val factory: BaseViewHolderAbstractFactory) : ListAdapter<BaseDataItem, BaseViewHolder>(BaseDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return factory.makeViewHolder(viewType, parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}

class BaseDiffCallback : DiffUtil.ItemCallback<BaseDataItem>() {

    override fun areItemsTheSame(oldItem: BaseDataItem, newItem: BaseDataItem): Boolean {
        return oldItem.itemsTheSame(newItem)
    }


    override fun areContentsTheSame(oldItem: BaseDataItem, newItem: BaseDataItem): Boolean {
        return oldItem.contentsTheSame(newItem)
    }
}