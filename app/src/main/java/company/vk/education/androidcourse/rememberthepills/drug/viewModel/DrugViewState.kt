package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.components.models.TextedItem

data class DrugViewState(
    val drugId: Long,
    val drugItems: Array<out TextedItem>,
    val screenMode: FormScreenMode,
    var selectedDrugTypeItem: TextedItem,
    var drugNameText: String?,
    val measurementItems: Array<out TextedItem>,
    var selectedMeasurementItem: TextedItem,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DrugViewState

        if (drugId != other.drugId) return false
        if (!drugItems.contentEquals(other.drugItems)) return false
        if (screenMode != other.screenMode) return false
        if (selectedDrugTypeItem != other.selectedDrugTypeItem) return false
        if (drugNameText != other.drugNameText) return false
        if (!measurementItems.contentEquals(other.measurementItems)) return false
        if (selectedMeasurementItem != other.selectedMeasurementItem) return false

        return true
    }

    override fun hashCode(): Int {
        var result = drugId.hashCode()
        result = 31 * result + drugItems.contentHashCode()
        result = 31 * result + screenMode.hashCode()
        result = 31 * result + selectedDrugTypeItem.hashCode()
        result = 31 * result + (drugNameText?.hashCode() ?: 0)
        result = 31 * result + measurementItems.contentHashCode()
        result = 31 * result + selectedMeasurementItem.hashCode()
        return result
    }
}