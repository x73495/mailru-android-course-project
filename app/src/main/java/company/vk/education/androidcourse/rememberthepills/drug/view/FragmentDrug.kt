package company.vk.education.androidcourse.rememberthepills.drug.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.drug.viewModel.DrugViewModel

class FragmentDrug : Fragment() {

    val args: FragmentDrugArgs by navArgs()
    val drugViewModel: DrugViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drug, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (args.action) {
            "add" -> {
                view.findViewById<Button>(R.id.button_drug_remove).visibility = View.GONE
                view.findViewById<Button>(R.id.button_drug_save).setText(R.string.add)
            }
            "edit" -> {
                view.findViewById<TextInputEditText>(R.id.input_text_drug_name)
                    .setText("Препарат с ID ${args.idOfDrug}")

                view.findViewById<Button>(R.id.button_drug_save).setText(R.string.save)
            }
        }

        val itemsType = listOf(
            getString(R.string.drug_type_tablet),
            getString(R.string.drug_type_inhaler),
            getString(R.string.drug_type_spray),
            getString(R.string.drug_type_injection),
            getString(R.string.drug_type_drops),
            getString(R.string.drug_type_ointment),
            getString(R.string.drug_type_powder),
            getString(R.string.drug_type_suppository),
            getString(R.string.drug_type_potion)
        )
        val adapterItemsMeasurement =
            ArrayAdapter(requireContext(), R.layout.list_item, itemsType)
        (view.findViewById<AutoCompleteTextView>(R.id.input_text_drug_type)).setAdapter(
            adapterItemsMeasurement
        )

        view.findViewById<Button>(R.id.button_drug_save).setOnClickListener {
            it.findNavController().popBackStack()
            // TODO: actual save
        }

        view.findViewById<Button>(R.id.button_drug_remove).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.drug_removal_confirmation_title))
                .setMessage(getString(R.string.drug_removal_confirmation_message))
                .setNegativeButton(getString(R.string.removal_confirmation_answer_no)) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(getString(R.string.removal_confirmation_answer_yes)) { _, _ ->
                    // TODO: actual deletion
                    it.findNavController().popBackStack()
                }
                .show()
        }
    }
}