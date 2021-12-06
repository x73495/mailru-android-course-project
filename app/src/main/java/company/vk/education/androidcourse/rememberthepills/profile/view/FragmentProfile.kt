package company.vk.education.androidcourse.rememberthepills.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseRecyclerViewAdapter
import company.vk.education.androidcourse.rememberthepills.components.base.model.BaseRouting
import company.vk.education.androidcourse.rememberthepills.components.base.utils.DividerItemDecorationFactory
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.databinding.FragmentProfileBinding
import company.vk.education.androidcourse.rememberthepills.profile.view.adapter.ProfileDiffUtilCallback
import company.vk.education.androidcourse.rememberthepills.profile.view.adapter.ProfileViewHolderFactory
import company.vk.education.androidcourse.rememberthepills.profile.viewModel.ProfileRouting
import company.vk.education.androidcourse.rememberthepills.profile.viewModel.ProfileViewModel
import company.vk.education.androidcourse.rememberthepills.profile.viewModel.ProfileViewModelFactory

class FragmentProfile : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val profileViewModel: ProfileViewModel by viewModels() {
        ProfileViewModelFactory(
            ResourceProvider(requireContext())
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        _binding = binding

        val viewHolderFactory = ProfileViewHolderFactory()
        val diffUtilCallback = ProfileDiffUtilCallback()
        val adapter = BaseRecyclerViewAdapter(viewHolderFactory, diffUtilCallback)
        val dividerItemDecoratorFactory = DividerItemDecorationFactory()
        binding.profileRecyclerView.adapter = adapter
        binding.profileRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.profileRecyclerView.addItemDecoration(
            dividerItemDecoratorFactory.create(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        subscribeViewModel()
        return binding.root
    }

    private fun subscribeViewModel() {
        val adapter = binding.profileRecyclerView.adapter as BaseRecyclerViewAdapter
        profileViewModel.presentationModel.observe(viewLifecycleOwner, {
            adapter.submitList(it.listItems)
        })
        profileViewModel.routingModel.observe(viewLifecycleOwner, {
            handleRouting(it)
        })
    }

    private fun handleRouting(routing: BaseRouting) {
        when(routing) {
            is ProfileRouting.DrugList -> {
                val action = FragmentProfileDirections.actionFragmentProfileToFragmentDrugList()
                findNavController().navigate(action)
            }
            else -> {
                return
            }
        }
        profileViewModel.routingDidHandle()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}