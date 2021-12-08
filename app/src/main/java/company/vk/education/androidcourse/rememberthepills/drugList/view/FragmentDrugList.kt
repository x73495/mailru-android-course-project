package company.vk.education.androidcourse.rememberthepills.drugList.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import company.vk.education.androidcourse.rememberthepills.RTPApplication
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseRecyclerViewAdapter
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting
import company.vk.education.androidcourse.rememberthepills.components.base.utils.DividerItemDecorationFactory
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.course.view.FragmentCourseArgs
import company.vk.education.androidcourse.rememberthepills.databinding.FragmentDrugListBinding
import company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.DrugListDiffUtilCallback
import company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.DrugListViewHolderFactory
import company.vk.education.androidcourse.rememberthepills.drugList.viewModel.DrugListRouting
import company.vk.education.androidcourse.rememberthepills.drugList.viewModel.DrugListViewModel
import company.vk.education.androidcourse.rememberthepills.drugList.viewModel.DrugListViewModelFactory

class FragmentDrugList : Fragment() {

    private var _binding: FragmentDrugListBinding? = null
    private val binding: FragmentDrugListBinding get() = _binding!!

    private val args: FragmentDrugListArgs by navArgs()

    private val drugListViewModel: DrugListViewModel by viewModels() {
        DrugListViewModelFactory(
            args.sourceType,
            ResourceProvider(requireContext()),
            (activity?.application as RTPApplication).drugListRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDrugListBinding.inflate(inflater, container, false)
        _binding = binding

        val viewHolderFactory = DrugListViewHolderFactory()
        val diffUtilCallback = DrugListDiffUtilCallback()
        val adapter = BaseRecyclerViewAdapter(viewHolderFactory, diffUtilCallback)
        val dividerItemDecoratorFactory = DividerItemDecorationFactory()
        binding.drugListRecyclerView.adapter = adapter
        binding.drugListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.drugListRecyclerView.addItemDecoration(
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
        val adapter = binding.drugListRecyclerView.adapter as BaseRecyclerViewAdapter
        drugListViewModel.presentationModel.observe(viewLifecycleOwner, {
            adapter.submitList(it.listItems)
        })
        drugListViewModel.routingModel.observe(viewLifecycleOwner, {
            handleRouting(it)
        })
    }

    private fun setupHandlers() {
        binding.buttonDrugAdd.setOnClickListener {
            handleRouting(DrugListRouting.drugCreation)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun handleRouting(routing: BaseRouting) {
        when(routing) {
            is DrugListRouting.CourseCreation -> {
                routing.let {
                    val action = FragmentDrugListDirections.actionFragmentDrugListToFragmentCourse(
                        FormScreenMode.CREATING
                    ).setDrugId(it.drugId)
                    findNavController().navigate(action)
                }
            }
            is DrugListRouting.DrugCreation -> {
                val action = FragmentDrugListDirections.actionFragmentDrugListToFragmentDrug(FormScreenMode.CREATING)
                findNavController().navigate(action)
            }
            is DrugListRouting.DrugEditing -> {
                routing.let {
                    val action = FragmentDrugListDirections.actionFragmentDrugListToFragmentDrug(
                        FormScreenMode.EDITING
                    ).setDrugId(routing.drugId)
                    findNavController().navigate(action)
                }
            }
            else -> {
                return
            }
        }
        drugListViewModel.routingDidHandle()
    }
}