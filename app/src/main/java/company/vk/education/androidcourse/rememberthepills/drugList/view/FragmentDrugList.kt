package company.vk.education.androidcourse.rememberthepills.drugList.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.components.models.FormScreenMode
import company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.DrugListAdapter
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugEntry
import company.vk.education.androidcourse.rememberthepills.drugList.viewModel.DrugListViewModel

class FragmentDrugList : Fragment() {

    private val args: FragmentDrugListArgs by navArgs()
    private val drugListViewModel: DrugListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drug_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.button_to_drug_add).setOnClickListener {
            val action = FragmentDrugListDirections.actionFragmentDrugListToFragmentDrug(FormScreenMode.CREATING)
            it.findNavController().navigate(action)
        }

        val drugEntries = generateDrugEntries().toMutableList()

        val recycler: RecyclerView = view.findViewById(R.id.recycler_drug_list)
        val adapter = DrugListAdapter(drugEntries, args.intent)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    // TODO TEMPORARY
    private fun generateDrugEntries(): List<DrugEntry> {
        return listOf(
            DrugEntry(
                "\$названиеПрепарата0",
                "Свеча",
                1
            ),
            DrugEntry(
                "\$названиеПрепарата1",
                "Капли",
                2
            ),
            DrugEntry(
                "\$названиеПрепарата2",
                "Мазь",
                3
            ),
            DrugEntry(
                "\$названиеПрепарата3",
                "Спрей",
                4
            ),
            DrugEntry(
                "\$названиеПрепарата4",
                "Порошок",
                5
            ),
            DrugEntry(
                "\$названиеПрепарата5",
                "Микстура",
                6
            ),
            DrugEntry(
                "\$названиеПрепарата6",
                "Спрей",
                7
            ),
            DrugEntry(
                "\$названиеПрепарата7",
                "Ингалятор",
                8
            )
        )
    }
}