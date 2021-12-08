package company.vk.education.androidcourse.rememberthepills.schedule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import company.vk.education.androidcourse.rememberthepills.RTPApplication
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseRecyclerViewAdapter
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting
import company.vk.education.androidcourse.rememberthepills.components.base.utils.DividerItemDecorationFactory
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.course.viewModel.CourseRoutingModel
import company.vk.education.androidcourse.rememberthepills.databinding.FragmentScheduleBinding
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugListSourceType
import company.vk.education.androidcourse.rememberthepills.schedule.view.adapter.ScheduleDiffUtilCallback
import company.vk.education.androidcourse.rememberthepills.schedule.view.adapter.ScheduleListViewHolderFactory
import company.vk.education.androidcourse.rememberthepills.schedule.viewModel.ScheduleListRouting
import company.vk.education.androidcourse.rememberthepills.schedule.viewModel.ScheduleListViewModel
import company.vk.education.androidcourse.rememberthepills.schedule.viewModel.ScheduleListViewModelFactory

class FragmentSchedule : Fragment() {

    private var _binding: FragmentScheduleBinding? = null
    private val binding: FragmentScheduleBinding get() = _binding!!

    private val scheduleListViewModel: ScheduleListViewModel by viewModels() {
        ScheduleListViewModelFactory(
            ResourceProvider(requireContext()),
            (activity?.application as RTPApplication).scheduleListRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentScheduleBinding.inflate(inflater, container, false)
        _binding = binding

        val viewHolderFactory = ScheduleListViewHolderFactory()
        val diffUtilCallback = ScheduleDiffUtilCallback()
        val adapter = BaseRecyclerViewAdapter(viewHolderFactory, diffUtilCallback)
        val dividerItemDecoratorFactory = DividerItemDecorationFactory()
        binding.recyclerViewSchedule.adapter = adapter
        binding.recyclerViewSchedule.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSchedule.addItemDecoration(
            dividerItemDecoratorFactory.create(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        subscribeViewModel()
        setupHandlers()
        return binding.root
    }

    private fun subscribeViewModel() {
        val adapter = binding.recyclerViewSchedule.adapter as BaseRecyclerViewAdapter
        scheduleListViewModel.presentationModel.observe(viewLifecycleOwner, {
            adapter.submitList(it.listItems)
        })
        scheduleListViewModel.routingModel.observe(viewLifecycleOwner, {
            handleRouting(it)
        })
    }

    private fun setupHandlers() {
        binding.buttonScheduleSave.setOnClickListener {
            handleRouting(ScheduleListRouting.drugList)
        }
    }

    private fun handleRouting(routing: BaseRouting) {
        when(routing) {
            is ScheduleListRouting.DrugList -> {
                val action = FragmentScheduleDirections.actionFragmentScheduleToFragmentDrugList(DrugListSourceType.SCHEDULE)
                findNavController().navigate(action)
            }
            is ScheduleListRouting.EditingCourse -> {
                routing.let {
                    val action = FragmentScheduleDirections.actionFragmentScheduleToFragmentCourse(
                        FormScreenMode.EDITING
                    ).setCourseId(it.courseId).setDrugId(it.drugId)
                    findNavController().navigate(action)
                }
            }
            else -> {
                return
            }
        }
        scheduleListViewModel.routingDidHandle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}