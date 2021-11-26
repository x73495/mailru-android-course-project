package company.vk.education.androidcourse.rememberthepills.components.base.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolderAbstractFactory

class BaseRecyclerViewAdapter(
    private val factory: BaseViewHolderAbstractFactory,
    private val diffUtilCalback: DiffUtil.ItemCallback<BaseDataItem>
) : ListAdapter<BaseDataItem, BaseViewHolder>(
    diffUtilCalback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return factory.makeViewHolder(viewType, parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position), payload = null)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val payload = payloads.firstOrNull()
        if (payload is BasePayload) {
            holder.bind(getItem(position), payload = payload)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }
}