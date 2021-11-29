package company.vk.education.androidcourse.rememberthepills.course.view.adapter

import android.view.ViewGroup
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolder
import company.vk.education.androidcourse.rememberthepills.components.base.viewHolder.BaseViewHolderAbstractFactory
import company.vk.education.androidcourse.rememberthepills.components.form.viewHolder.*

class CourseViewHolderFactory : BaseViewHolderAbstractFactory() {

    private val formViewHolderFactory = FormViewHolderFactory()

    override fun makeViewHolder(viewType: Int, parent: ViewGroup): BaseViewHolder {
        return when(viewType) {
            CourseViewType.addIntakeTime.viewType -> CourseAddIntakeTimeViewHolder.from(parent)
            CourseViewType.intakeTime.viewType -> CourseIntakeTimeViewHolder.from(parent)
            CourseViewType.courseDrugTitle.viewType -> CourseDrugTitleViewHolder.from(parent)
            else -> formViewHolderFactory.makeViewHolder(viewType, parent)
        }
    }
}