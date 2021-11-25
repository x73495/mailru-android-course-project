package company.vk.education.androidcourse.rememberthepills.course.viewModel

import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseDataItem
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.form.model.SectionHeaderDataItem

class CourseViewModelMapper(
    private val resourceProvider: ResourceProvider,
    private val delegate: CourseViewModelMapper.Delegate
) {
    interface Delegate {

    }

    enum class ViewId {
        DRUG_NAME_SECTION_HEADER
    }

    fun createPresentationModel(viewState: CourseViewState): CoursePresentationModel {
        return CoursePresentationModel(createDataItems(viewState))
    }

    private fun createDataItems(viewState: CourseViewState): List<BaseDataItem> {
        val drugNameSectionHeader = SectionHeaderDataItem(
            id = ViewId.DRUG_NAME_SECTION_HEADER.ordinal,
            text = resourceProvider.getString(R.string.drug)
        )
        return listOf(drugNameSectionHeader)
    }
}