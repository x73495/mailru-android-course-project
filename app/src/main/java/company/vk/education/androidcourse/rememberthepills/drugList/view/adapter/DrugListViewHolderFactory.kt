package company.vk.education.androidcourse.rememberthepills.drugList.view.adapter

import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolderAbstractFactory
import company.vk.education.androidcourse.rememberthepills.components.form.viewHolder.FormViewHolderFactory

class DrugListViewHolderFactory: BaseViewHolderAbstractFactory() {

    private val formViewHolderFactory = FormViewHolderFactory()

    override fun makeViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder {
        return when(viewType) {
            DrugListViewType.listItem.viewType -> DrugListViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }
}