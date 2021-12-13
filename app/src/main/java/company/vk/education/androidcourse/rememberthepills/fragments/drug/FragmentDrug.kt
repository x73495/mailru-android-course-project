package company.vk.education.androidcourse.rememberthepills.fragments.drug

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.room.course.DBClientCourses
import company.vk.education.androidcourse.rememberthepills.room.drug.DBClientDrugs
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug
import kotlinx.coroutines.launch

class FragmentDrug : Fragment() {

    private val args: FragmentDrugArgs by navArgs()

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
                val dbClient = DBClientDrugs(requireActivity())
                lifecycleScope.launch {
                    dbClient.getByID(args.idOfDrug)
                }
                val entityToEdit = dbClient.awaitResult() as EntityDrug

                view.findViewById<TextInputEditText>(R.id.input_text_drug_name)
                    .setText(entityToEdit.name)

                view.findViewById<AutoCompleteTextView>(R.id.input_text_drug_type)
                    .setText(entityToEdit.type)

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
            getString(R.string.drug_type_mixture)
        )
        val adapterItemsMeasurement =
            ArrayAdapter(requireContext(), R.layout.list_item, itemsType)
        (view.findViewById<AutoCompleteTextView>(R.id.input_text_drug_type)).setAdapter(
            adapterItemsMeasurement
        )

        view.findViewById<Button>(R.id.button_drug_save).setOnClickListener {
            val newDrugName = view.findViewById<TextInputEditText>(R.id.input_text_drug_name)
            val newDrugType = view.findViewById<AutoCompleteTextView>(R.id.input_text_drug_type)

            val dbClient = DBClientDrugs(requireActivity())

            // TODO fix literals
            if (args.action == "edit") {
                lifecycleScope.launch {
                    dbClient.getByID(args.idOfDrug)

                }
                val entityForUpdate: EntityDrug = dbClient.awaitResult() as EntityDrug

                entityForUpdate.name = newDrugName.text.toString()
                entityForUpdate.type = newDrugType.text.toString()

                lifecycleScope.launch {
                    dbClient.update(entityForUpdate)
                }
                dbClient.awaitResult()

            } else if (args.action == "add") {
                lifecycleScope.launch {
                    dbClient.insertAll(EntityDrug(name=newDrugName.text.toString(), type=newDrugType.text.toString()))
                }
                dbClient.awaitResult()
            }

            it.findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.button_drug_remove).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.drug_removal_confirmation_title))
                .setMessage(getString(R.string.drug_removal_confirmation_message))
                .setNegativeButton(getString(R.string.removal_confirmation_answer_no)) { dialog, _ ->
                    dialog.cancel()
                }
                .setPositiveButton(getString(R.string.removal_confirmation_answer_yes)) { _, _ ->
                    val dbClientCourses = DBClientCourses(requireActivity())
                    lifecycleScope.launch {
                        dbClientCourses.deleteByDrugID(args.idOfDrug)
                    }
                    dbClientCourses.awaitResult()

                    val dbClientDrugs = DBClientDrugs(requireActivity())
                    lifecycleScope.launch {
                        dbClientDrugs.deleteByID(args.idOfDrug)
                    }
                    dbClientDrugs.awaitResult()

                    it.findNavController().popBackStack()
                }
                .show()
        }
    }
}