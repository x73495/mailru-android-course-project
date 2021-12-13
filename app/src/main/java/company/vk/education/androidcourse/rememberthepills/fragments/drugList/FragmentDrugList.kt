package company.vk.education.androidcourse.rememberthepills.fragments.drugList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.room.drug.DBClientDrugs
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug
import kotlinx.coroutines.launch

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
        val fab = view.findViewById<FloatingActionButton>(R.id.button_to_drug_add)
        fab.setOnClickListener {
            val action = FragmentDrugListDirections.actionFragmentDrugListToFragmentDrug(
                "add",
                -1
            )
            it.findNavController().navigate(action)
        }

        val dbClient = DBClientDrugs(requireActivity())
        lifecycleScope.launch {
            dbClient.getAll()
        }

        val drugEntries = dbClient.awaitResult() as List<EntityDrug>

        val recycler: RecyclerView = view.findViewById(R.id.recycler_drug_list)
        val adapter = DrugListAdapter(drugEntries, args.intent)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                    fab.hide()
                else if (dy < 0)
                    fab.show()
            }
        })
    }
}