package company.vk.education.androidcourse.rememberthepills.drug.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import company.vk.education.androidcourse.rememberthepills.components.adapter.form.BaseRecyclerViewAdapter
import company.vk.education.androidcourse.rememberthepills.components.form.viewHolder.FormViewHolderFactory
import company.vk.education.androidcourse.rememberthepills.databinding.FragmentDrugBinding
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewModel
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewModelFactory

class FragmentDrug : Fragment() {

    private var _binding: FragmentDrugBinding? = null
    private val binding: FragmentDrugBinding get() = _binding!!

    val args: FragmentDrugArgs by navArgs()
    val drugViewModel: DrugViewModel by viewModels() {
        DrugViewModelFactory(args.mode, args.drugId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDrugBinding.inflate(inflater, container, false)
        _binding = binding

        val viewHolderFactory = FormViewHolderFactory()
        binding.drugFormRecyclerView.adapter = BaseRecyclerViewAdapter(viewHolderFactory)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (args.mode) {
//            "add" -> {
//                view.findViewById<Button>(R.id.button_drug_remove).visibility = View.GONE
//                view.findViewById<Button>(R.id.button_drug_save).setText(R.string.add)
//            }
//            "edit" -> {
//                view.findViewById<AutoCompleteTextView>(R.id.input_text_drug_name)
//                    .setText("Препарат с ID ${args.idOfDrug}")
//                view.findViewById<AutoCompleteTextView>(R.id.input_text_drug_type)
//                    .setText("Тип препарата с ID ${args.idOfDrug}")
//
//                view.findViewById<Button>(R.id.button_drug_save).setText(R.string.save)
//            }
        }

//        val itemsType = DrugTypeItem.values().map { getString(it.textId) }
//        val adapterItemsMeasurement =
//            ArrayAdapter(requireContext(), R.layout.item_text_view_drop_down_list, itemsType)
//        (view.findViewById<AutoCompleteTextView>(R.id.input_text_drug_type)).setAdapter(
//            adapterItemsMeasurement
//        )

//        view.findViewById<Button>(R.id.button_drug_save).setOnClickListener {
//            it.findNavController().popBackStack()
//            // TODO: actual save
//        }
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
}