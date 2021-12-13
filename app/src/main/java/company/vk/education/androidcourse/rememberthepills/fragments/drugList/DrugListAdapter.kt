package company.vk.education.androidcourse.rememberthepills.fragments.drugList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import company.vk.education.androidcourse.rememberthepills.R
import company.vk.education.androidcourse.rememberthepills.room.drug.EntityDrug

class DrugListAdapter(private val drugEntries: List<EntityDrug>, private val intentForEntries: String) :
    RecyclerView.Adapter<DrugListAdapter.DrugEntryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrugEntryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_drug_entry, parent, false)

        return DrugEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrugEntryViewHolder, position: Int) {
        holder.bind(drugEntries[position], intentForEntries)
    }

    override fun getItemCount() = drugEntries.size

    class DrugEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameOfDrug: TextView = itemView.findViewById(R.id.text_drug_list_name_of_drug)
        private val typeOfDrug: TextView = itemView.findViewById(R.id.text_drug_list_type_of_drug)

        fun bind(drugEntry: EntityDrug, intentForEntries: String) {
            // TODO: do sth with all these hardcode values
            if (intentForEntries == "choose") {
                itemView.findViewById<LinearLayout>(R.id.layout_drug_entry_info).setOnClickListener {
                    val action = FragmentDrugListDirections.actionFragmentDrugListToFragmentCourse("add", drugEntry.id, -1)
                    it.findNavController().navigate(action)
                }
            }
            else if (intentForEntries == "edit") {
                val buttonEntryEdit: Button = itemView.findViewById(R.id.button_drug_entry_edit)
                buttonEntryEdit.visibility = View.VISIBLE
                buttonEntryEdit.setOnClickListener {
                    val action = FragmentDrugListDirections.actionFragmentDrugListToFragmentDrug("edit", drugEntry.id)
                    it.findNavController().navigate(action)
                }
            }

            nameOfDrug.text = drugEntry.name
            typeOfDrug.text = drugEntry.type
        }
    }
}