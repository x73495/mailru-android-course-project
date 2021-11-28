package company.vk.education.androidcourse.rememberthepills.course.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseDiffCallback
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseRecyclerViewAdapter
import company.vk.education.androidcourse.rememberthepills.components.base.utils.DividerItemDecorationFactory
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.course.view.adapter.CourseViewHolderFactory
import company.vk.education.androidcourse.rememberthepills.course.viewModel.*
import company.vk.education.androidcourse.rememberthepills.databinding.FragmentCourseBinding

class FragmentCourse : Fragment() {

    private var _binding: FragmentCourseBinding? = null
    private val binding: FragmentCourseBinding get() = _binding!!

    private val args: FragmentCourseArgs by navArgs()
    private val courseViewModel: CourseViewModel by viewModels() {
        CourseViewModelFactory(
            args.mode,
            courseId = args.courseId,
            drugId = args.drugId,
            ResourceProvider(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCourseBinding.inflate(inflater, container, false)
        _binding = binding

        val viewHolderFactory = CourseViewHolderFactory()
        val diffUtilCallback = BaseDiffCallback()
        val adapter = BaseRecyclerViewAdapter(viewHolderFactory, diffUtilCallback)
        val dividerItemDecoratorFactory = DividerItemDecorationFactory()
        binding.courseFormRecyclerView.adapter = adapter
        binding.courseFormRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.courseFormRecyclerView.addItemDecoration(
            dividerItemDecoratorFactory.create(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        subscribeViewModel()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeViewModel() {
        val adapter = binding.courseFormRecyclerView.adapter as BaseRecyclerViewAdapter
        courseViewModel.presentationModel.observe(viewLifecycleOwner, {
            when(it) {
                is CourseDataPresentationModel -> {
                    adapter.submitList(it.listItems)
                }
                is CourseDateDialogPresentationModel -> {
                    showDatePicker(it)
                }
                is CourseTimeDialogPresentationModel -> {
                    showTimePicker(it)
                }
                else -> {}
            }
        })
    }

    private fun showDatePicker(presentationModel: CourseDateDialogPresentationModel) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(presentationModel.title)
            .setSelection(presentationModel.selectedDateInMilliseconds ?: MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        datePicker.addOnPositiveButtonClickListener {
            when(presentationModel.dateType) {
                CourseDateDialogPresentationModel.DateType.STARTED -> {
                    courseViewModel.selectedStartedDate(datePicker.selection)
                }
                CourseDateDialogPresentationModel.DateType.ENDED -> {
                    courseViewModel.selectedEndedDate(datePicker.selection)
                }
            }
            datePicker.dismiss()
        }

        datePicker.addOnNegativeButtonClickListener {
            courseViewModel.cancelledInputDate()
        }

        datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
    }

    private fun showTimePicker(presentationModel: CourseTimeDialogPresentationModel) {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setHour(presentationModel.getHours())
            .setMinute(presentationModel.getMinutes())
            .setTitleText(presentationModel.title)
            .build()
        timePicker.addOnPositiveButtonClickListener {
            courseViewModel.selectedTime(timePicker.hour, timePicker.minute)
            timePicker.dismiss()
        }
        timePicker.addOnNegativeButtonClickListener {
            courseViewModel.cancelledInputTime()
            timePicker.dismiss()
        }
        timePicker.show(requireActivity().supportFragmentManager, timePicker.toString())
    }
}