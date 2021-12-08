package company.vk.education.androidcourse.rememberthepills.profile.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.model.BasePayload
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.databinding.ItemProfileListBinding
import company.vk.education.androidcourse.rememberthepills.profile.view.adapter.item.ProfileDataItem

class ProfileViewHolder private constructor(private val binding: ItemProfileListBinding) : BaseViewHolder(binding.root) {
    override fun bind(item: BaseDataItem, payload: BasePayload?) {
        // TODO: нужен ли весь payload ?
        val item = payload ?: item
        if (item is ProfileDataItem) {
            binding.textProfileListTitle.text = item.title
            binding.containerLayout.setOnClickListener {
                item.selectionHandler()
            }
        }
    }

    companion object {
        fun from(parent: ViewGroup): ProfileViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProfileListBinding.inflate(layoutInflater, parent, false)
            return ProfileViewHolder(binding)
        }
    }
}