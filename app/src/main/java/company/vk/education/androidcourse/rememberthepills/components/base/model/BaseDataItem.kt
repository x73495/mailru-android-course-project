package company.vk.education.androidcourse.rememberthepills.components.base.model

interface BaseDataItem {
    var viewType: Int
    fun contentsTheSame(item: BaseDataItem): Boolean
    fun itemsTheSame(item: BaseDataItem): Boolean
}