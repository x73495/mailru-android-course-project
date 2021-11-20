package company.vk.education.androidcourse.rememberthepills.components.base.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem

open class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(item: BaseDataItem) {}
}