package company.vk.education.androidcourse.rememberthepills.drug.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseDiffCallback
import company.vk.education.androidcourse.rememberthepills.components.base.adapter.BaseRecyclerViewAdapter
import company.vk.education.androidcourse.rememberthepills.components.base.utils.DividerItemDecorationFactory
import company.vk.education.androidcourse.rememberthepills.components.base.utils.ResourceProvider
import company.vk.education.androidcourse.rememberthepills.components.form.viewHolder.FormViewHolderFactory
import company.vk.education.androidcourse.rememberthepills.databinding.FragmentDrugBinding
import company.vk.education.androidcourse.rememberthepills.drug.view.adapter.DrugDiffUtilCallback
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewModel
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewModelFactory

class FragmentDrug : Fragment() {

    private var _binding: FragmentDrugBinding? = null
    private val binding: FragmentDrugBinding get() = _binding!!

    private val args: FragmentDrugArgs by navArgs()
    private val drugViewModel: DrugViewModel by viewModels() {
        DrugViewModelFactory(args.mode, args.drugId, ResourceProvider(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDrugBinding.inflate(inflater, container, false)
        _binding = binding

        val viewHolderFactory = FormViewHolderFactory()
        val diffUtilCallback = DrugDiffUtilCallback()
        val adapter = BaseRecyclerViewAdapter(viewHolderFactory, diffUtilCallback)
        val dividerItemDecoratorFactory = DividerItemDecorationFactory()
        binding.drugFormRecyclerView.adapter = adapter
        binding.drugFormRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.drugFormRecyclerView.addItemDecoration(
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
        val adapter = binding.drugFormRecyclerView.adapter as BaseRecyclerViewAdapter
        drugViewModel.listItems.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

//
//        view.findViewById<Button>(R.id.button_drug_remove).setOnClickListener {
//            MaterialAlertDialogBuilder(requireContext())
//                .setTitle(getString(R.string.drug_removal_confirmation_title))
//                .setMessage(getString(R.string.drug_removal_confirmation_message))
//                .setNegativeButton(getString(R.string.removal_confirmation_answer_no)) { dialog, _ ->
//                    dialog.cancel()
//                }
//                .setPositiveButton(getString(R.string.removal_confirmation_answer_yes)) { _, _ ->
//                    // TODO: actual deletion
//                    it.findNavController().popBackStack()
//                }
//                .show()
//        }
}