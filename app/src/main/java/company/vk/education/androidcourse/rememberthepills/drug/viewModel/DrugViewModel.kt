package company.vk.education.androidcourse.rememberthepills.drug.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode

class DrugViewModel(
    private val mode: FormScreenMode,
    private val id: Int?) : ViewModel() {
}

class DrugViewModelFactory(
    private val mode: FormScreenMode,
    private val id: Int?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DrugViewModel(mode, id) as T
    }
}