package company.vk.education.androidcourse.rememberthepills.drugList.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.drugList.view.adapter.DrugListAdapter
import company.vk.education.androidcourse.rememberthepills.drugList.model.DrugEntry

class FragmentDrugList : Fragment() {

    private val args: FragmentDrugListArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drug_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<FloatingActionButton>(R.id.button_to_drug_add).setOnClickListener {
            val action = FragmentDrugListDirections.actionFragmentDrugListToFragmentDrug(
                    "add",
                    -1
                )
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
                "Фуфломицин",
                "Свеча",
                1
            ),
            DrugEntry(
                "Фуфломицин",
                "Свеча",
                2
            ),
            DrugEntry(
                "препаратНейм",
                "Спрей",
                3
            )
        )
    }
}